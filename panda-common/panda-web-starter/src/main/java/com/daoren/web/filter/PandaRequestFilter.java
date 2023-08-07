package com.daoren.web.filter;

import com.daoren.common.base.entity.Result;
import com.daoren.thread.context.RequestContext;
import com.daoren.thread.worker.AbstractProcessWorker;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志过滤器
 *
 * @author peng_da
 * @version :
 * @date 2022/5/27 16:06
 * @since :
 */
@Slf4j
public class PandaRequestFilter extends OncePerRequestFilter {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private static String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("JSON 转换失败" + e.getLocalizedMessage());
            return "";
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        LocalDateTime startTime = LocalDateTime.now();
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = uri.substring(contextPath.length());
        //静态资源 跳过
        if (url.contains(".")) {
            filterChain.doFilter(request, response);
            return;
        }
//		输出请求体
        String requestBody = "";
        String requestContentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        final Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.size() != 0) {
            RequestContext.REQUEST_PARAMS.set(objectMapper.readValue(objectMapper.writeValueAsString(parameterMap), Map.class));
            requestContentType = null;
        }

        if (requestContentType != null) {
//			xml json
            if (requestContentType.startsWith(MediaType.APPLICATION_JSON_VALUE) || requestContentType.startsWith(MediaType.APPLICATION_XML_VALUE)) {
                requestBody = getRequestBody(request);
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
                request = new HttpServletRequestWrapper(request) {
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ByteArrayServletInputStream(byteArrayInputStream);
                    }
                };
//		    普通表单提交
            } else if (requestContentType.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
                requestBody = toJson(request.getParameterMap());
//			文件表单提交
            } else if (requestContentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                requestBody = getFormParam(request);
            }
            RequestContext.REQUEST_PARAMS.set(objectMapper.readValue(requestBody, Map.class));
        }

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        response = new HttpServletResponseWrapper(response) {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new TeeServletOutputStream(super.getOutputStream(), byteArrayOutputStream);
            }
        };

        LocalDateTime endTime = LocalDateTime.now();
        String responseBody = "";
        Result result = null;
        try {
            filterChain.doFilter(request, response);

            String contentType = response.getHeader(HttpHeaders.CONTENT_TYPE);
            if (contentType != null && contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                responseBody = byteArrayOutputStream.toString();
            }
            endTime = LocalDateTime.now();
            AbstractProcessWorker.submit(url, responseBody, startTime, endTime, null);
        } catch (Exception e) {
            AbstractProcessWorker.submit(url, responseBody, startTime, endTime, e);
        } finally {
            // todo 好像没有清理掉
            RequestContext.clearAll();
        }
    }

    private String getRequestBody(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        if (contentLength <= 0) {
            return "";
        }
        try {
            return IOUtils.toString(request.getReader());
        } catch (IOException e) {
            log.error("获取请求体失败", e);
            return "";
        }
    }

    private String getFormParam(HttpServletRequest request) {
        MultipartResolver resolver = new StandardServletMultipartResolver();
        MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);

        Map<String, Object> param = new HashMap<>();
        Map<String, String[]> parameterMap = mRequest.getParameterMap();
        if (!parameterMap.isEmpty()) {
            param.putAll(parameterMap);
        }
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        if (!fileMap.isEmpty()) {
            for (Map.Entry<String, MultipartFile> fileEntry : fileMap.entrySet()) {
                MultipartFile file = fileEntry.getValue();
                param.put(fileEntry.getKey(), file.getOriginalFilename() + "(" + file.getSize() + " byte)");
            }
        }
        return toJson(param);
    }
}

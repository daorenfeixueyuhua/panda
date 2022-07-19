package com.daoren.web.filter;

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
public class LogFilter extends OncePerRequestFilter {
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
        long requestTime = System.currentTimeMillis();
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
        }

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        response = new HttpServletResponseWrapper(response) {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new TeeServletOutputStream(super.getOutputStream(), byteArrayOutputStream);
            }
        };

        filterChain.doFilter(request, response);

        long costTime = System.currentTimeMillis() - requestTime;
        String responseBody = "";
//		暂定只有json 输出响应体
        String contentType = response.getHeader(HttpHeaders.CONTENT_TYPE);
        if (contentType != null && contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            responseBody = byteArrayOutputStream.toString();
        }
        // todo 需要进行显示优化
        if (response.getStatus() >= 200 && response.getStatus() < 300) {
            log.info("URL:{}, total time:{} ms, responseCode:{}, requestBody:{}, responseBody:{}", url, costTime, response.getStatus(), requestBody, responseBody);
        } else {
            log.error("URL:{}, total time:{} ms, responseCode:{}, requestBody:{}, responseBody:{}", url, costTime, response.getStatus(), requestBody, responseBody);
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

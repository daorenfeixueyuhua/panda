package com.daoren.web.handler;

import com.daoren.web.annotation.ApiVersion;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * API version condition
 *
 * @author peng_da
 * @version :
 * @date 2022/4/24 15:56
 * @since :
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    public static final Pattern VERSION_PREFIX_PATTERN
            = Pattern.compile("/v([0-9]+\\.{0,1}[0-9]{0,2})/");
    private ApiVersion apiVersion;

    public ApiVersionCondition(ApiVersion apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        // 通过uri匹配版本号
        System.out.println(request.getRequestURI());
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (m.find()) {
            // 获得符合匹配条件的ApiVersionCondition
            System.out.println("groupCount:" + m.groupCount());
            double version = Double.valueOf(m.group(1));
            if (version >= getApiVersion().version()) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 当出现多个符合匹配条件的ApiVersionCondition，优先匹配版本号较大的
        return other.getApiVersion().version() >= getApiVersion().version() ? 1 : -1;
    }

    public ApiVersion getApiVersion() {
        return apiVersion;
    }
}

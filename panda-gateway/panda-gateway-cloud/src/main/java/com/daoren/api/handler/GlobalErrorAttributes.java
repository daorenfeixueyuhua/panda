package com.daoren.api.handler;

import com.daoren.common.entity.Result;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
/**
 *
 * @author peng_da
 * @date  2022/8/30 14:10
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);
        final Result fail = Result.fail(HttpStatus.BAD_REQUEST.value(), error.getMessage(), null);
        // status 是必须的，gateway内部需要使用这个属性
        fail.put("status", HttpStatus.BAD_REQUEST.value());
        return fail;
    }
}

package com.daoren.api.factory;

import com.daoren.api.service.RemotePreProxyService;
import com.daoren.common.entity.R;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.vo.DbResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author peng_da
 * @date  2022/8/17 9:42
 */
@Slf4j
@Component
public class RemotePreProxyFallbackFactory implements FallbackFactory<RemotePreProxyService> {
    @Override
    public RemotePreProxyService create(Throwable cause) {
        log.error("dbagent service error", cause);
        return new RemotePreProxyService() {
            @Override
            public R<DbResult> preProxy(RequestParams params) {
                return R.fail("dbagent service error: " + cause.getMessage());
            }

            @Override
            public R<List<DbResult>> preProxyList(List<RequestParams> paramsList) {
                return R.fail("dbagent service error: " + cause.getMessage());
            }
        };
    }
}

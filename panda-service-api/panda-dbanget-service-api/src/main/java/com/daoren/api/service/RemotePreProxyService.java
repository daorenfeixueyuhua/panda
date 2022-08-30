package com.daoren.api.service;

import com.daoren.api.factory.RemotePreProxyFallbackFactory;
import com.daoren.common.constant.ServiceNameConstants;
import com.daoren.common.entity.R;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.vo.DbResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author peng_da
 * @date 2022/8/10 17:14
 */
@FeignClient(contextId = "remotePreProxyService", value = ServiceNameConstants.DBAGENT_SERVIVE,
        fallbackFactory = RemotePreProxyFallbackFactory.class)
public interface RemotePreProxyService {
    /**
     * 执行sql
     *
     * @param params :
     * @return java.lang.Object
     * @author peng_da
     * @since 2022/8/11 10:34
     */
    @PostMapping(value = "/preProxy", consumes = {MediaType.APPLICATION_JSON_VALUE})
    R<DbResult> preProxy(@RequestBody RequestParams params);

    /**
     * 执行多次sql
     *
     * @param paramsList :
     * @return java.lang.Object
     * @author peng_da
     * @since 2022/8/11 10:34
     */
    @PostMapping(value = "/preProxy/list", consumes = {MediaType.APPLICATION_JSON_VALUE})
    R<List<DbResult>> preProxyList(@RequestBody List<RequestParams> paramsList);
}

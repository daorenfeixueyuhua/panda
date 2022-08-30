package com.daoren.dbagent.service;

import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.vo.DbResult;

import java.util.List;

/**
 * @author peng_da
 * @date 2022/8/10 17:14
 */
public interface PreProxyService {
    /**
     * 执行sql
     * @author peng_da
     * @since 2022/8/11 10:34
     * @param params :
     * @return java.lang.Object
     */
    DbResult preProxy(RequestParams params);

    /**
     * 执行多次sql
     * @author peng_da
     * @since 2022/8/11 10:34
     * @param paramsList :
     * @return java.lang.Object
     */
    List<DbResult> preProxyList(List<RequestParams> paramsList);
}

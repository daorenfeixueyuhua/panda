package com.daoren.test.controller;

import com.daoren.api.service.RemotePreProxyService;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.vo.DbResult;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peng_da
 * @date  2022/8/17 9:52
 */
@RequestMapping("/dbagent")
@RestController
public class DbAgentController {

    @Autowired
    private RemotePreProxyService remotePreProxyService;

    @ResponseBody
    @ResponseResult
    @RequestMapping("/execute")
    public DbResult execute(@RequestBody RequestParams params){
        return remotePreProxyService.preProxy(params).getData();
    }
}

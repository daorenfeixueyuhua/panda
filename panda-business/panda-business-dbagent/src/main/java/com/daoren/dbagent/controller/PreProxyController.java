package com.daoren.dbagent.controller;

import com.daoren.dbagent.annotation.SqlValidation;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.vo.DbResult;
import com.daoren.dbagent.service.PreProxyService;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author peng_da
 * @date  2022/8/11 11:10
 */
@Controller
@RequestMapping("/preProxy")
public class PreProxyController {
    private PreProxyService baseService;

    @Autowired
    public void setBaseService(PreProxyService baseService) {
        this.baseService = baseService;
    }

    /**
     * 执行sql
     * @author peng_da
     * @date  2022/8/11 11:13
     */
    @ResponseBody
    @ResponseResult
    @PostMapping
    public DbResult preProxy(@RequestBody @SqlValidation RequestParams params){
        return baseService.preProxy(params);
    }
    /**
     * 执行sql
     * @author peng_da
     * @date  2022/8/11 11:13
     */
    @ResponseBody
    @ResponseResult
    @PostMapping("/list")
    public List<DbResult> preProxyList(@RequestBody @SqlValidation List<RequestParams> params){
        return baseService.preProxyList(params);
    }
}

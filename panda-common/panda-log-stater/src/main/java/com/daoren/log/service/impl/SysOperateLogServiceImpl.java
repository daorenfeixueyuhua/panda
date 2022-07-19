package com.daoren.log.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.log.entity.SysOperateLog;
import com.daoren.log.mapper.SysOperateLogMapper;
import com.daoren.log.service.ISysOperateLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author pengda
 * @since 2022-02-11
 */
@Service
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog> implements ISysOperateLogService {

}

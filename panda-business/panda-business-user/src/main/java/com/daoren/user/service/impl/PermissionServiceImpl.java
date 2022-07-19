package com.daoren.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.user.mapper.PermissionMapper;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}

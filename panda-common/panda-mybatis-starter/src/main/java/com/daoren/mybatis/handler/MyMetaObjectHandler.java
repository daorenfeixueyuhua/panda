package com.daoren.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充功能
 *
 * @author pengda
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "sysCreateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "sysCreateUser", String.class, "panda");
        this.strictInsertFill(metaObject, "sysUpdateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "sysUpdateUser", String.class, "panda");
        this.strictInsertFill(metaObject, "sysEnable", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "sysUpdateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "sysUpdateUser", String.class, "panda");
    }

}

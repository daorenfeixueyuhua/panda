package com.daoren.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author peng_da
 * @date 2022/11/16 16:08
 */
@Mapper
public interface TestMapper {
    String getUuid();

    @Select("select uuid() from dual;")
    String getUuidAnn();
}

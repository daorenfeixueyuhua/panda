package com.daoren.dbagent.mapper;

import com.daoren.dbagent.model.dto.SqlParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 执行Mapping
 *
 * @author peng_da
 * @version :
 * @date 2022/8/10 16:54
 * @since :
 */
@Mapper
public interface PublicMapper {

    /**
     * 获取值
     *
     * @param sql : sql语句
     * @return java.util.List<java.util.LinkedHashMap < java.lang.String, java.lang.Object>>
     * @author peng_da
     * @since 2022/8/10 17:00
     */
    @Select({"${sql}"})
    List<LinkedHashMap<String, Object>> getPublicItems(@Param("sql") String sql);

    /**
     * 获取值条件
     *
     * @param sql    : sql语句
     * @param params : 参数
     * @return java.util.List<java.util.LinkedHashMap < java.lang.String, java.lang.Object>>
     * @author peng_da
     * @since 2022/8/10 17:01
     */
    @Select({"${sql}"})
    List<LinkedHashMap<String, Object>> getItems(@Param("sql") String sql, @Param("params") SqlParams params);

    /**
     * 更新Item
     *
     * @param sql    : sql语句
     * @param params : 参数
     * @return int
     * @author peng_da
     * @since 2022/8/10 17:02
     */
    @Select({"${sql}"})
    int updateItem(@Param("sql") String sql, @Param("params") SqlParams params);

    /**
     * 统计item
     *
     * @param sql    : sql语句
     * @param params : 参数
     * @return int
     * @author peng_da
     * @since 2022/8/10 17:03
     */
    @Select({"${sql}"})
    int count(@Param("sql") String sql, @Param("params") SqlParams params);
}

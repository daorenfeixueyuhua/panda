package com.daoren.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.mybatis.annotation.DataScope;
import com.daoren.test.model.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 个人信息表 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-07-20
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    /**
     * 数据权限测试
     * @author peng_da
     * @since 2022/8/16 16:25
     * @param id : id
     * @return java.util.List<com.daoren.test.model.entity.Person>
     */
    @DataScope
    List<Person> getAllById(@Param("id") String id);



    int updateBatch(List<Person> personList);
}

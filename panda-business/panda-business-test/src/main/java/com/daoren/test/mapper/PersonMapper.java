package com.daoren.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.test.model.entity.Person;
import org.apache.ibatis.annotations.Mapper;

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

}

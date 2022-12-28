package com.daoren.trans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.trans.model.entity.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author peng_da
 * @date 2022/12/26 14:21
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}

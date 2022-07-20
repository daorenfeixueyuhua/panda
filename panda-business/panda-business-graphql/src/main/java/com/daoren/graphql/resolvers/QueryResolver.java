package com.daoren.graphql.resolvers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.daoren.graphql.mapper.OperateLogMapper;
import com.daoren.graphql.model.entity.OperateLog;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author peng_da
 * @version :
 * @date 2022/7/19 10:16
 * @since :
 */
@Component
public class QueryResolver implements GraphQLQueryResolver {
    private final OperateLogMapper operateLogMapper;

    public QueryResolver(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }


    public OperateLog getOne(String id) {
        return operateLogMapper.selectById(id);
    }

    /**
     * logs
     *
     * @param entity :
     * @return java.util.List<com.daoren.graphsqlql.model.entity.OperateLog>
     * @author peng_da
     * @since 2022/7/19 14:56
     */
    public List<OperateLog> logs(OperateLog entity) {
        final LambdaQueryWrapper<OperateLog> queryWrapper = new QueryWrapper<OperateLog>().lambda();
        queryWrapper.setEntity(entity);
        return operateLogMapper.selectList(queryWrapper);
    }
}

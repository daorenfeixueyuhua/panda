package com.daoren.mybatis.interceptor;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.daoren.mybatis.annotation.DataScope;
import com.daoren.mybatis.constant.DataScopeConstant;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * 参数级别数据权限拦截器
 *
 * @author peng_da
 * @date 2022/8/16 15:51
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class ParamsDataScopeInterceptor implements Interceptor {
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        if (ArrayUtils.isNotEmpty(args)){
            MappedStatement mp = (MappedStatement) args[0];
            // 获取DataScope注解信息
            final DataScope annotation = getDataScopeAnnotation(mp.getId());
            if (annotation!=null){
                // todo 具体构建sql的实现
                if (args[1]==null){
                    args[1] = new MapperMethod.ParamMap<>();
                }
                Object object = args[1];
                if (object instanceof Map){
                    ((Map)object).put(DataScopeConstant.DATA_SCOPE_SQL, "AND 1 = 1");
                }else if (object instanceof MapperMethod.ParamMap){
                    ((MapperMethod.ParamMap)object).put(DataScopeConstant.DATA_SCOPE_SQL, "AND 1 = 1");
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 获取{@link DataScope}注解信息
     * @author peng_da
     * @since 2022/8/16 16:00
     * @param namespace : 全方法名称
     * @return com.daoren.mybatis.annotation.DataScope
     */
    private static DataScope getDataScopeAnnotation(String namespace) throws ClassNotFoundException {
        if (StringUtils.isBlank(namespace)){
            return null;
        }
        final String className = namespace.substring(0, namespace.lastIndexOf("."));
        final String methodName = namespace.substring(namespace.lastIndexOf(".")+1);
        final Method[] methods = Class.forName(className).getMethods();
        final Optional<Method> currentMethod = Arrays.stream(methods).filter(i -> methodName.equals(i.getName())).findFirst();
        return currentMethod.get().getAnnotation(DataScope.class);
    }
}

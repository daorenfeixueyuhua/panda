## 系统总览

```txt
panda
    docker-run  -- docker-compose配置
    panda-auth  -- 权限相关
    panda-business  -- 具体业务
        panda-business-user     -- 用户服务
        panda-business-log      -- 日志服务
        panda-business-test     -- test服务
    panda-common        -- 公共依赖
        panda-web-starter   -- web服务依赖
        panda-graphsql-starter  -- graphsql依赖
        panda-mybatis-starter   -- mybatis依赖
        panda-json-startre      -- json依赖
        panda-log-starter       -- 接口日志依赖
        panda-swagger-starter   -- swagger依赖
        panda-oauth-core        -- 权限core
        panda-oauth-client      -- 权限client
        panda-redis-starter     -- redis依赖
    panda-gateway       -- 网关
    panda-service-api   -- rpc 定义
```




INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (1, 'panda-buiness-test', 'DEFAULT_GROUP', 'spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ALGORTHIM_DATABASE
    username: root
    password: 123456
  redis:
    database: 0
    host: 127.0.0.1
    port: 6380
    password: redis
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        max-wait: -1ms
        min-idle: 0
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: sys_enable # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
  gen:
    output-dir: ${project.path}\\src\\main\\java
    mapper-xml: ${project.path}\\src\\main\\resources\\mapper
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
    parent: com.daoren.test
  type-enums-package: com.daoren.*.enums', 'f374adce73ec116f0b03ca882e470a0b', '2022-03-29 07:02:27', '2022-03-29 07:03:08', 'nacos', '172.18.0.1', '', '', '', '', '', 'text', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (2, 'panda-buiness-file', 'DEFAULT_GROUP', 'spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ALGORTHIM_DATABASE
    username: root
    password: 123456
  servlet:
    multipart:
      enabled: true # 开启文件上传支持
      file-size-threshold: 0 # 文件写入磁盘的阀值
      location: D:\\Sources\\Files # 上传文件的临时保存位置
      max-request-size: 10MB # 多文件上传时文件的总大小
      max-file-size: 1MB # 上传的单个文件的最大大小
      resolve-lazily: false # 文件是否延迟解析
  resources:
    static-locations: file:${spring.servlet.multipart.location}
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: sys_enable # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
  gen:
    output-dir: ${project.path}\\src\\main\\java
    mapper-xml: ${project.path}\\src\\main\\resources\\mapper
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
    parent: com.daoren.file
  type-enums-package: com.daoren.*.enums', '0f2558009806d2872ba5c351ac41e821', '2022-03-29 07:02:27', '2022-03-29 07:03:20', 'nacos', '172.18.0.1', '', '', '', '', '', 'text', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (3, 'application-dev', 'DEFAULT_GROUP', 'spring:
  redis:
    database: 0
    host: 127.0.0.1
    port: 6380', '9d7a2a15e3761f5e376d766e552db9e7', '2022-03-29 07:02:27', '2022-03-29 07:02:27', null, '172.18.0.1', '', '', null, null, null, 'text', null);
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (4, 'panda-gateway-api', 'DEFAULT_GROUP', 'spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
        - id: panda-buiness-test
          uri: lb://panda-buiness-test
          predicates:
            - Path=/api/test/**
          filters:
            - StripPrefix=2
            - AddResponseHeader=X-Response-Default-Foo, Default-Bar
        - id: panda-buiness-auth
          uri: lb://panda-buiness-auth
          predicates:
            - Path=/api/auth/**
          filters:
            - StripPrefix=2
            - AddResponseHeader=X-Response-Default-Foo, Default-Bar', '4f1953d8e161884d4ddd336706d89938', '2022-03-29 07:02:27', '2022-03-29 07:02:27', null, '172.18.0.1', '', '', null, null, null, 'text', null);
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (5, 'panda-buiness-auth', 'DEFAULT_GROUP', 'spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ALGORTHIM_DATABASE
    username: root
    password: 123456
  redis:
    database: 0
    host: 127.0.0.1
    port: 6380
    password: redis
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        max-wait: -1ms
        min-idle: 0
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: sys_enable # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
  gen:
    output-dir: ${project.path}\\src\\main\\java
    mapper-xml: ${project.path}\\src\\main\\resources\\mapper
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
    parent: com.daoren.auth
  type-enums-package: com.daoren.*.enums', '7a4c5debe45abe97607b2bc30fdb715b', '2022-03-29 07:02:27', '2022-03-29 07:03:43', 'nacos', '172.18.0.1', '', '', '', '', '', 'text', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (9, 'panda-buiness-user', 'DEFAULT_GROUP', 'spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ALGORTHIM_DATABASE
    username: root
    password: 123456
  redis:
    database: 0
    host: 127.0.0.1
    port: 6380
    password: redis
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        max-wait: -1ms
        min-idle: 0
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: sys_enable # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
  gen:
    output-dir: ${project.path}\\src\\main\\java
    mapper-xml: ${project.path}\\src\\main\\resources\\mapper
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
    parent: com.daoren.user
  type-enums-package: com.daoren.*.enums', '566d7d1c553da4f1298af1abf788cae2', '2022-03-29 07:26:58', '2022-03-29 07:26:58', null, '172.18.0.1', '', '', null, null, null, 'text', null);
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (10, 'panda-buiness-graphql', 'DEFAULT_GROUP', 'spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ALGORTHIM_DATABASE
    username: root
    password: 123456
  redis:
    database: 0
    host: 127.0.0.1
    port: 6380
    password: redis
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        max-wait: -1ms
        min-idle: 0
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: sys_enable # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
  gen:
    output-dir: ${project.path}\\src\\main\\java
    mapper-xml: ${project.path}\\src\\main\\resources\\mapper
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
    parent: com.daoren.graphsqlql
  type-enums-package: com.daoren.*.enums', '1d6ded36b8883b48e546db966a78b062', '2022-07-15 07:27:00', '2022-07-15 08:13:36', 'nacos', '172.20.0.1', '', '', '', '', '', 'text', '');
INSERT INTO nacos_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema) VALUES (13, 'panda-business-camunda', 'DEFAULT_GROUP', 'spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/camunda
    username: root
    password: 123456
  redis:
    database: 0
    host: 127.0.0.1
    port: 6380
    password: redis
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        max-wait: -1ms
        min-idle: 0
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: sys_enable # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
  gen:
    output-dir: ${project.path}\\src\\main\\java
    mapper-xml: ${project.path}\\src\\main\\resources\\mapper
    url: ${spring.datasource.url}
    username: ${spring.datasource.username}
    password: ${spring.datasource.password}
    parent: com.daoren.test
  type-enums-package: com.daoren.*.enums
camunda:
  bpm:
    #配置账户密码来访问Camunda自带的管理界面
    admin-user:
      id: admin
      password: admin
      first-name: admin
    filter:
      create: All tasks
    #指定数据库类型
#    database:
#      type: mysql
    #禁止自动部署resources下面的bpmn文件
    auto-deployment-enabled: false
    #禁止index跳转到Camunda自带的管理界面，默认true
#    webapp:
#      index-redirect-enabled: false', '07099864ecb6ccfd2315b04ce8b952e1', '2022-07-21 08:38:16', '2022-07-21 08:38:16', null, '172.20.0.1', '', '', null, null, null, 'yaml', null);
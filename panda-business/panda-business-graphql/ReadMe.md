## Graphql 后端服务器搭建

### 导入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>panda-business</artifactId>
        <groupId>com.daoren</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>panda-business-graphql</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <!--其他依赖-->
        <!-- UI界面 所需要的  starter   注入器为 GraphiQLAutoConfiguration -->
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphiql-spring-boot-starter</artifactId>
        </dependency>
        <!--    核心控制器的 starter  源码是根据GraphQLWebAutoConfiguration 中的graphQLServletRegistrationBean 向spring 容器注入的Servlet实现
               该Servlet有几个默认实现 SimpleGraphQLHttpServlet 和 OsgiGraphQLHttpServlet Osgi为一个动态模型系统（没听过，默认使用Simple）
             -->
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java-tools</artifactId>
        </dependency>
    </dependencies>

</project>
```

### 页面访问

在浏览器中访问：

```http request
http://127.0.0.1:9094/graphiql
```

例子：

```text
query logQuery{
  logs(entity:{
    operateUserName: "panda"
  }){
		... logFields
  }
}


fragment logFields on OperateLog{
    id
    # 操作人
    operateUserId
    # 操作人姓名
    operateUserName
    # 操作sql语句
    operateSql
    # 入参
    operateParams
    # 返回结果
    operateResult
    # 操作标题
    operateTitle
    # 操作Url
    operateUrl
    # 操作地址
    operateAddress
    # 操作类型
    operateType
    # 操作业务类型
    operateBusinessType
    # 操作状态
    operateBusinessStatus
    # 失败原因
    operateErrorMessage
}


```
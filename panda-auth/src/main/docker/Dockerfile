# 基于openjdk 镜像
FROM openjdk:8-jdk-alpine

# 将本地文件夹挂在到当前容器
VOLUME /tmp

# 复制文件到容器
ADD panda-auth.jar app.jar

# 声明需要暴露的端口
EXPOSE 8081

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
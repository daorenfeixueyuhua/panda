# 搭建mysql主从复制集群

## 环境说明

docker desktop：V4.10.1

mysql image：mysql:laste（8.0.28）

## 准备docker环境

```shell
docker pull mysql
```

## 准备mysql实例

### mysql-master

```shell
docker run --name mysql-master --privileged=true -v D:\Software\docker\mysql-cluster\master-data:/var/lib/mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
```

进入容器内部修改`/etc/mysql/my.cnf` 文件

注：msql容器内部可能没有`vim`，分别执行 `apt-get update`和`apt-get install vim`安装 `vim`

再 `/etc/mysql/my.cnf` 增加以下内容

```cnf
log-bin=mysql-bin # 开启二进制文件
server-id=1 # 设置server-id
```

重启容器

执行 `mysql -root -p` 登录mysql，执行`show master status;` 查看master信息，例如

| File            | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
| --------------- | -------- | ------------ | ---------------- | ----------------- |
| mysql-bin.00000 | 2150     |              |                  |                   |
|                 |          |              |                  |                   |

注：position 可能会变

#### 创建复制数据的用户

执行以下sql语句：

```mysql
# 创建用户
create user test identified by '123456';
show grants for test;
grant replication slave on *.* to 'test'@'%';
flush privileges;
use mysql;
# 查看用户密码
select host, user, authentication_string, plugin from user;
# 更换密码模式
ALTER USER 'test'@'%'  IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER;
ALTER USER 'test'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
FLUSH PRIVILEGES;
```

### mysql-slave

```shell
docker run --name mysql-slave --privileged=true -v D:\Software\docker\mysql-cluster\slave-data:/var/lib/mysql -p 3308:3306 --link mysql-master:master -e MYSQL_ROOT_PASSWORD=root -d mysql

```

进入容器内部修改`/etc/mysql/my.cnf` 文件

注：msql容器内部可能没有`vim`，分别执行 `apt-get update`和`apt-get install vim`安装 `vim`

再 `/etc/mysql/my.cnf` 增加以下内容

```cnf
server-id=2 # 设置server-id
```

重启容器

登录root之后分别执行：

```mysql
change master to master_host='master', master_user='test', master_password='123456', master_port=3306, master_log_file='mysql-bin.000001', master_log_pos=1001, master_connect_retry=30;
start slave;
show slave status\G
```

如果看到结果：

```txt
*************************** 1. row ***************************
               Slave_IO_State: Waiting for source to send event
                  Master_Host: master
                  Master_User: test
                  Master_Port: 3306
                Connect_Retry: 30
              Master_Log_File: mysql-bin.000001
          Read_Master_Log_Pos: 1001
               Relay_Log_File: ff878b838338-relay-bin.000002
                Relay_Log_Pos: 326
        Relay_Master_Log_File: mysql-bin.000001
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
            .....
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set, 1 warning (0.00 sec)
```

             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
    # 看到上面两条输出，说明成功；

## 同步测试

再mysql-master中执行：

```mysql
create database cluster_demo;
use cluster_demo;
create table sys_user(
  id int auto_increment primary key comment 'index',
  name varchar(100) comment 'name',
  age int comment 'age'
) comment 'user table';
insert into sys_user (name, age) value ('panda', 21);
```

再mysql_slave 中执行：

```mysql
show databases;
use cluster_demo;
select * from sys_user;
```

如果输出正常说明主从数据库搭建成功。

## 参考文档

[主从数据库 Slave_IO_Running: Connecting问题解决]: https://blog.csdn.net/weixin_43879261/article/details/111483712

[docker搭建MySQL主从集群]: https://zhuanlan.zhihu.com/p/86966579


#  创建用户，并分配权限
# create user test identified by '123456';
# show grants for test;
# grant replication slave on *.* to 'test'@'%';
# flush privileges;

# show master status;

# create user test identified by '123456';
# use mysql;
# # 查看用户密码
# select host, user, authentication_string, plugin from user;
# # 更换密码模式
# ALTER USER 'test'@'%'  IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER;
# ALTER USER 'test'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
# FLUSH PRIVILEGES;


create database cluster_demo;
use cluster_demo;
create table sys_user
(
    id   int auto_increment primary key comment 'index',
    name varchar(100) comment 'name',
    age  int comment 'age'
) comment 'user table';
insert into sys_user (name, age) value ('panda', 21);
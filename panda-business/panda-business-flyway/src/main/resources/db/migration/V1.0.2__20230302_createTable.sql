select '开始执行数据库脚本';

create table sys_test
(
    id   int primary key auto_increment comment 'id',
    name varchar(32) comment 'name'
);

insert into sys_test (name) value ('panda');


select '结束执行数据库脚本';
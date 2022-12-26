change master to master_host ='mysql_master', master_user ='test', master_password ='123456', master_port =3306, master_log_file ='mysql-bin.000003', master_log_pos =1533, master_connect_retry =30;
start slave;
show slave status;

stop slave;
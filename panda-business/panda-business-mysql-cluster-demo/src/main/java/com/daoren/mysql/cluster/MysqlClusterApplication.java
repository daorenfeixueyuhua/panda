package com.daoren.mysql.cluster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主从复制数据库测试应用
 *
 * @author peng_da
 * @date 2022/12/26 11:03
 */
@MapperScan("com.daoren.**.mapper")
@SpringBootApplication
public class MysqlClusterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlClusterApplication.class, args);
    }
}

package com.daoren.mybatis.generator;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.daoren.mybatis.entity.BaseEntity;
import com.daoren.mybatis.properties.MybatisPlusGeneratorProperties;

import java.util.Collections;

/**
 * 代码生成器
 *
 * @author peng_da
 * @version :
 * @date 2022/2/16 16:59
 * @since :
 */
public class MybatisPlusGenerator {
    private MybatisPlusGeneratorProperties properties;

    public void setProperties(MybatisPlusGeneratorProperties properties) {
        this.properties = properties;
    }

    public void run(final String[] include) {
        FastAutoGenerator.create(properties.getUrl(), properties.getUsername(), properties.getPassword())
                .globalConfig(builder -> {
                    builder.author(properties.getAuthor()) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(properties.getOutputDir()); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(properties.getParent()) // 设置父包名
//                            .moduleName(null) // 设置父包模块名
                            .controller("controller")
                            .entity("model.entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, properties.getMapperXml())); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(include) // 设置需要生成的表名
                            .addTablePrefix("sys_") // 去除标表明前缀
                    ;
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder() // Entity 策略配置
                            .superClass(BaseEntity.class) // 父类
                            .enableLombok() // 开启lombok
                            .addSuperEntityColumns(properties.getSuperEntityColumns())
                            .naming(NamingStrategy.underline_to_camel)//下划线转驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel)//下划线转驼峰命名
                    ;
                })
                .strategyConfig(builder -> {
                    builder.controllerBuilder() // Controller 策略配置
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle() // 开启生成@RestController 控制器
                    ;
                })
                .strategyConfig(builder -> {
                    builder.serviceBuilder() // Service 策略配置
                            .formatServiceFileName("%sService") // 格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl") // 格式化 service 接口文件名称
                    ;
                })
                .strategyConfig(builder -> {
                    builder.mapperBuilder() // Mapper 策略配置
                            .superClass(BaseMapper.class) // 设置父类
                            .enableMapperAnnotation() // 开启 @Mapper 注解
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .enableBaseColumnList() // 启用 BaseColumnList
//                            .cache(MyMapperCache.class)
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                    ;
                })

                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

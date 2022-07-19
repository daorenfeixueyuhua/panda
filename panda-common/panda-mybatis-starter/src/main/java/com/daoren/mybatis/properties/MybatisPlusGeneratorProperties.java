package com.daoren.mybatis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 分页配置
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 11:15
 * @since :
 */
@ConfigurationProperties("mybatis-plus.gen")
public class MybatisPlusGeneratorProperties {
    private ClassLoader classLoader;

    private String url;
    private String username;
    private String password;
    private String[] superEntityColumns = new String[]{
            "id", "sys_create_time", "sys_create_user",
            "sys_update_time", "sys_update_user",
            "sys_delete_time", "sys_delete_user"
    };
    /**
     * <p>like /projects/demo/src/main/java/</p>
     */
    private String outputDir;
    /**
     * <p>like /projects/demo/src/resources/mapper</p>
     */
    private String mapperXml;
    /**
     * <p>like com.example.test</p>
     */
    private String parent;
    private String author = "daoren";
    private String[] include;

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getSuperEntityColumns() {
        return superEntityColumns;
    }

    public void setSuperEntityColumns(String[] superEntityColumns) {
        this.superEntityColumns = superEntityColumns;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getMapperXml() {
        return mapperXml;
    }

    public void setMapperXml(String mapperXml) {
        this.mapperXml = mapperXml;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String[] include) {
        this.include = include;
    }
}

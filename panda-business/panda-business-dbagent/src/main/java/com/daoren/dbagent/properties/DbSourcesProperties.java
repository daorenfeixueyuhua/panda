package com.daoren.dbagent.properties;

import com.daoren.dbagent.model.dto.DbSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 数据库配置参数
 *
 * @author peng_da
 * @date 2022/8/10 17:57
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dbsources")
public class DbSourcesProperties {
    List<DbSource> sources;

}

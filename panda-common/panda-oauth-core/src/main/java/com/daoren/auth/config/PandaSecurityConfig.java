package com.daoren.auth.config;

import com.daoren.auth.properties.PandaSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author peng_da
 * @date  2022/8/26 14:43
 */
@Configuration
@EnableConfigurationProperties({PandaSecurityProperties.class})
public class PandaSecurityConfig {
}

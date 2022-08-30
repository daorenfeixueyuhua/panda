package com.daoren.dbagent.model.dto;

import lombok.Data;

/**
 *
 * @author peng_da
 * @date  2022/8/11 10:18
 */
@Data
public class DbSource {
    private String key;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}

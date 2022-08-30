package com.daoren.dbagent.model.dto;

import com.daoren.dbagent.enums.SqlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求实体
 * @author peng_da
 * @date  2022/8/10 17:16
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RequestParams implements Serializable {
    private static final long serialVersionUID = 4279942073099346061L;
    private String tip;
    private String db = "api";
    private String code;
    private SqlType type;
    private SqlParams params;
}

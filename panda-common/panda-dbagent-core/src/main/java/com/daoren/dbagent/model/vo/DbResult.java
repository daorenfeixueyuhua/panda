package com.daoren.dbagent.model.vo;

import com.daoren.dbagent.enums.SqlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询结果
 *
 * @author peng_da
 * @date 2022/8/11 14:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DbResult implements Serializable {
    private static final long serialVersionUID = -1418678747936093659L;
    private String tip;
    private SqlType type;
    private Object data;
    private String msg;
    private long mills;
    private boolean success;
}

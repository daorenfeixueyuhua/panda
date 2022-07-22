package com.daoren.camunda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author peng_da
 * @version :
 * @date 2022/7/22 14:47
 * @since :
 */
@Data
@AllArgsConstructor
public class Leave {
    private String id;
    private String content;
    private Date date;
}

package com.daoren.seate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDO {
    /**
     * 订单编号
     **/
    private Integer id;

    /**
     * 用户编号
     **/
    private Long userId;

    /**
     * 产品编号
     **/
    private Long productId;

    /**
     * 支付金额
     **/
    private Integer payAmount;
}

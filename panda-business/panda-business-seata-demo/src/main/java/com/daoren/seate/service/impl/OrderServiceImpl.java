package com.daoren.seate.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.daoren.seate.mapper.OrderDao;
import com.daoren.seate.model.entity.OrderDO;
import com.daoren.seate.service.AccountService;
import com.daoren.seate.service.OrderService;
import com.daoren.seate.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Override
    @DS(value = "order-ds") // <1>
    @GlobalTransactional // <2>
    public Integer createOrder(Long userId, Long productId, Integer price) throws Exception {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // <3> 扣减库存
        productService.reduceStock(productId, amount);

        // <4> 扣减余额
        accountService.reduceBalance(userId, price);

        // <5> 保存订单
        final OrderDO order = OrderDO.builder().userId(userId).productId(productId).payAmount(amount * price).build();
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }
}

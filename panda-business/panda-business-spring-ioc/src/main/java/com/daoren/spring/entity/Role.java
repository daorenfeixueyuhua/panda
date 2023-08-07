package com.daoren.spring.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author peng_da
 * @date 2022/11/18 15:33
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class Role {
    public Role() {
        System.out.println("I am Role");
    }
}

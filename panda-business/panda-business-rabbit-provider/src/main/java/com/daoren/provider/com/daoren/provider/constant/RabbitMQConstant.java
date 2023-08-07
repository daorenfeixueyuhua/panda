package com.daoren.provider.com.daoren.provider.constant;

/**
 * MR常量
 *
 * @author peng_da
 * @date 2023/1/3 16:20
 */
@SuppressWarnings("all")
public class RabbitMQConstant {
    /**
     * 队列主题名称
     */
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmqDemoTopic";
    /**
     * Direct交换机
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";
    /**
     * Direct交换机和队列版绑定得匹配键
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";
    private RabbitMQConstant() {
    }
}

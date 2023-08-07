package com.daoren.provider.com.daoren.provider.service;

/**
 * @author peng_da
 * @date 2023/1/3 16:45
 */

public interface RabbitMQService {
    /**
     * 发送消息
     *
     * @param msg : message
     * @return java.lang.String
     * @author peng_da
     * @since 2023/1/3 16:46
     */
    String sendMsg(String msg);
}

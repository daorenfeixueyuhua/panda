package com.daoren.provider.com.daoren.provider.config;

import com.daoren.provider.com.daoren.provider.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author peng_da
 * @date 2023/1/3 15:08
 */
@SuppressWarnings("all")
@Configuration
public class DirectRabbitMQConfig implements BeanPostProcessor {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        return new Queue(RabbitMQConstant.RABBITMQ_DEMO_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        return new DirectExchange(RabbitMQConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDirect() {
        return BindingBuilder
                .bind(rabbitmqDemoDirectQueue())
                .to(rabbitmqDemoDirectExchange())
                .with(RabbitMQConstant.RABBITMQ_DEMO_DIRECT_ROUTING);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        final RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareQueue(rabbitmqDemoDirectQueue());
        rabbitAdmin.declareExchange(rabbitmqDemoDirectExchange());
        return null;
    }
}

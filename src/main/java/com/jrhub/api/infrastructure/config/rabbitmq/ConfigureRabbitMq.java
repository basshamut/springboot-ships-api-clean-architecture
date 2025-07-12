package com.jrhub.api.infrastructure.config.rabbitmq;

import com.jrhub.api.infrastructure.messaging.rabbitmq.RabbitMQReceiverUseCaseService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jrhub.api.utils.Constants.AUDIT_QUEUE;

@Configuration
@EnableRabbit
public class ConfigureRabbitMq {

    @Bean
    public Queue auditQueue() {
        return new Queue(AUDIT_QUEUE, false);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(AUDIT_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RabbitMQReceiverUseCaseService receiver, Jackson2JsonMessageConverter messageConverter) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, "receiveAuditMessage");
        adapter.setMessageConverter(messageConverter);
        return adapter;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
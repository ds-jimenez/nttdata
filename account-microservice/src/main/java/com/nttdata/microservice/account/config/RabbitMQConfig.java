package com.nttdata.microservice.account.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String client_queue = "clientQueue";
    public static final String client_queue_dlq = "clientQueueDlq";



    @Bean
    public Queue clientQueue() {
        return QueueBuilder.durable(client_queue)
                .withArgument("x-dead-letter-exchange", "") // default exchange
                .withArgument("x-dead-letter-routing-key", client_queue_dlq)
                .build();
    }

    @Bean
    public Queue clientQueueDlq() {
        return QueueBuilder.durable(client_queue_dlq).build();
    }

    // Listener personalizado para reintentos
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .maxAttempts(5)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build());
        return factory;
    }

}



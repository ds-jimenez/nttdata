package com.nttdata.microservice.client.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    public static final String client_queue = "clientQueue";
    public static final String client_queue_dlq = "clientQueueDlq";

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
    @Bean
    public ApplicationRunner runner(AmqpAdmin amqpAdmin) {
        return args -> {
            System.out.println("Forzando declaración de colas...");
            amqpAdmin.initialize();
        };
    }

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
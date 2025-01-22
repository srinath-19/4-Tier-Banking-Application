package com.example.bankapp.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;  // Autowire the redisTemplate

    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "redisChannel")
    public Message<?> receiveFromService(Message<?> message){
        System.out.println("This message is received from the service: " + message);
        return message;
    }

    @ServiceActivator(inputChannel = "redisChannel")
    public void sendMessageToQueue(Message<?> message){
        // Use the configured RedisTemplate with Jackson serializer
        redisTemplate.convertAndSend("Redis-Queue", message.getPayload());
    }

    @ServiceActivator(inputChannel = "receiverChannel")
    public void receiveFromQueue(Message<?> message){
        System.out.println("Received from Redis Queue: " + message);
    }
}


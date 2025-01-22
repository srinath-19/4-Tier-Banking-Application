package com.example.bankapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;

@Configuration
@EnableIntegration
@IntegrationComponentScan("com.example.bankapp")
@EnableAutoConfiguration
public class IntegrationConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        return factory;
    }

    @Bean
    public DirectChannel receiverChannel(){
        return new DirectChannel();
    }

    @Bean
    public RedisQueueMessageDrivenEndpoint consumerEndPoint(){
        RedisQueueMessageDrivenEndpoint endPoint = new RedisQueueMessageDrivenEndpoint("Redis-Queue", jedisConnectionFactory());
        endPoint.setOutputChannelName("receiverChannel");
        return endPoint;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Use Jackson2JsonRedisSerializer for serializing/deserializing objects to/from JSON
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);  // set the default serializer for both values and keys

        // Optional: If you want to specifically set key and value serializers:
        redisTemplate.setKeySerializer(RedisSerializer.string());  // String serializer for Redis keys
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // JSON serializer for Redis values

        return redisTemplate;
    }


}

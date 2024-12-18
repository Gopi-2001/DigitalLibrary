package com.minorProject.DigitalLibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.validation.ObjectError;

@Configuration
public class RedisConfiguration {
//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        RedisStandaloneConfiguration redisStandAloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandAloneConfiguration.setHostName("localhost");
//        redisStandAloneConfiguration.setPort(6379);
//
////        redisStandAloneConfiguration.setHostName("redis-15440.c275.us-east-1-4.ec2.redns.redis-cloud.com");
////        redisStandAloneConfiguration.setPort(15440);
////        redisStandAloneConfiguration.setPassword("your_password");
//
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandAloneConfiguration);
//        return lettuceConnectionFactory;
//    }


    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        return redisTemplate;
    }



}

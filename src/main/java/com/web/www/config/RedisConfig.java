package com.web.www.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * RedisTemplate配置
     *
     * @param redisConnectionFactory redis连接工厂
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建RedisTemplate对象
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 设置RedisConnectionFactory
        template.setConnectionFactory(redisConnectionFactory);
        // 创建Jackson2JsonRedisSerializer对象, 使用Jackson2JsonRedisSerialize 替换 redis默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = createJackson2JsonRedisSerializer();
        // 设置序列化规则
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // 设置key的序列化规则
        template.setKeySerializer(new StringRedisSerializer());

        // Value使用JSON序列化（推荐GenericJackson2JsonRedisSerializer）
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 初始化
        template.afterPropertiesSet();
        // 返回
        return template;
    }

    /**
     * 创建Jackson2JsonRedisSerializer对象
     *
     * @return
     */
    private Jackson2JsonRedisSerializer createJackson2JsonRedisSerializer() {
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // 设置objectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置属性可见性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 启用默认的类型
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 启用默认的类型
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        // 启用JavaTimeModule支持
        objectMapper.registerModule(new JavaTimeModule());
        // 禁用时间戳
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 设置objectMapper
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

}

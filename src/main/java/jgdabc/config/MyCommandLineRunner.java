package jgdabc.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        //把key的序列化方式改成字符串的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //把hashKey的序列化方式改成字符串的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //redisTemplate.setValueSerializer(new StringRedisSerializer());

        //设置value的序列化方式使用json的序列化方式（默认情况下是jdk序列化） （在项目开发中这4行代码写一次就行了，也是固定的）
        //对象映射,可以实现java对象 <---> json对象的相互转换
        ObjectMapper objectMapper = new ObjectMapper();
        //设置对象里面的字段的可见性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);// 可见度; 能见度; 能见距离; 可见性; 明显性;
        //设置多态行为的
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING);
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(objectMapper, Object.class));

        //hash值的序列化方式改为json序列化
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer(objectMapper, Object.class));

    }
}

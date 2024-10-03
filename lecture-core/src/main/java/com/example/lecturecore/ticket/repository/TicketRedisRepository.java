package com.example.lecturecore.ticket.repository;

import com.example.lecturecore.ticket.controller.dto.RedisVO;
import com.example.lecturecore.ticket.service.RedisOperation;
import com.example.lecturecore.ticket.service.RedisTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisTransaction redisTransaction;
    private final RedisOperation<RedisVO> operation;

    public List<Long> execute(RedisVO redisVO) {
        List<Object> execute = redisTransaction.execute(redisTemplate, operation, redisVO);
        List<Long> result = new ArrayList<>();
        for (Object o : execute) {
            result.add((Long) o);
        }
        return result;
    }

    public Long increment() {
        return redisTemplate
                .opsForValue()
                .increment("ticket_count");
    }

    public Long addKeyToSet(String value) {
        return redisTemplate.opsForSet()
                .add("applied-user", value);
    }

    public Long getSetCount(String key) {
        return redisTemplate.opsForSet()
                .size(key);
    }

    public Long addToSet(String key, String email) {
        return redisTemplate.opsForSet()
                .add(key, email);
    }
}

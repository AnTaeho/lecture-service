package com.example.lecturecore.ticket.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long increment() {
        return redisTemplate
                .opsForValue()
                .increment("ticket_count");
    }

    public Long add(String value) {
        return redisTemplate.opsForSet()
                .add("applied-user", value);
    }


}

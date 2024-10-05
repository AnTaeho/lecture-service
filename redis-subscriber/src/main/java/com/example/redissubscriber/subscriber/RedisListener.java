package com.example.redissubscriber.subscriber;

import com.example.redissubscriber.userticket.UserTicket;
import com.example.redissubscriber.userticket.UserTicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisListener implements MessageListener {

    private final ObjectMapper objectMapper;
    private final UserTicketRepository userTicketRepository;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = objectMapper.readValue(message.getBody(), String.class);

            String[] infos = publishMessage.split(":");

            userTicketRepository.save(new UserTicket(Long.parseLong(infos[0]), infos[1]));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

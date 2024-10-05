package com.example.kafkaconsumer.consumer;

import com.example.kafkaconsumer.dto.TicketEvent;
import com.example.kafkaconsumer.userticket.UserTicket;
import com.example.kafkaconsumer.userticket.UserTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreateConsumer {

    private final UserTicketRepository userTicketRepository;

    @KafkaListener(topics = "issue-ticket", groupId = "group_1")
    public void listener(TicketEvent ticketEvent) {
        System.out.println("ticketId = " + ticketEvent.ticketId() + "email = " + ticketEvent.email());
        userTicketRepository.save(new UserTicket(ticketEvent.ticketId(), ticketEvent.email()));
    }

}

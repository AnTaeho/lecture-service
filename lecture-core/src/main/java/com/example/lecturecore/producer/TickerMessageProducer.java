package com.example.lecturecore.producer;

import com.example.lecturecore.event.ticket.TicketEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TickerMessageProducer {

    private final KafkaTemplate<String, TicketEvent> kafkaTemplate;

    public void create(TicketEvent ticketEvent) {
        System.out.println("value = " + ticketEvent.ticketId() + ":" + ticketEvent.email());
        kafkaTemplate.send("issue-ticket", ticketEvent);
    }
}

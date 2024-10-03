package com.example.lecturecore.event.ticket;

import com.example.lecturecore.producer.TickerMessageProducer;
import com.example.lecturecore.ticket.domain.TicketOutbox;
import com.example.lecturecore.ticket.repository.TicketOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class TicketEventListener {

    private final TicketOutboxRepository outboxRepository;
    private final TickerMessageProducer tickerMessageProducer;

    @TransactionalEventListener(value = TicketEvent.class, phase = TransactionPhase.BEFORE_COMMIT)
    public void saveOutboxInfo(TicketEvent ticketEvent) {
        outboxRepository.save(new TicketOutbox(ticketEvent.ticketId(), ticketEvent.email()));
    }

    @Async
    @TransactionalEventListener(value = TicketEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void produceMessage(TicketEvent ticketEvent) {
        tickerMessageProducer.create(ticketEvent);
    }

}

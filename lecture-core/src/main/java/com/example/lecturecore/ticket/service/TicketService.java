package com.example.lecturecore.ticket.service;

import com.example.lecturecore.event.ticket.TicketEvent;
import com.example.lecturecore.ticket.controller.dto.TickerResponse;
import com.example.lecturecore.ticket.controller.dto.TicketRequest;
import com.example.lecturecore.ticket.domain.Ticket;
import com.example.lecturecore.ticket.repository.TicketRedisRepository;
import com.example.lecturecore.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketRedisRepository ticketRedisRepository;
    private final ApplicationEventPublisher publisher;

    public void issue(Long ticketId, String email) {
        String value = ticketId + ":" + email;
        Long result = ticketRedisRepository.add(value);
        if (result != 1) {
            return;
        }

        int amount = getAmount(ticketId);
        long count = ticketRedisRepository.increment();
        if (count > amount) {
            return;
        }

        publisher.publishEvent(new TicketEvent(ticketId, email));
    }

    public TickerResponse createTicket(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(
                ticketRequest.amount(),
                ticketRequest.lectureId()
        );
        Ticket savedTicket = ticketRepository.save(ticket);
        return new TickerResponse(savedTicket.getId());
    }

    private int getAmount(Long ticketId) {
        return ticketRepository.getTicketAmount(ticketId);
    }
}

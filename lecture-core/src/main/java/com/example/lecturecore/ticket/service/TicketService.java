package com.example.lecturecore.ticket.service;

import com.example.lecturecore.ticket.controller.dto.RedisVO;
import com.example.lecturecore.ticket.controller.dto.TickerResponse;
import com.example.lecturecore.ticket.controller.dto.TicketRequest;
import com.example.lecturecore.ticket.domain.Ticket;
import com.example.lecturecore.ticket.domain.UserTicket;
import com.example.lecturecore.ticket.repository.TicketRedisRepository;
import com.example.lecturecore.ticket.repository.TicketRepository;
import com.example.lecturecore.ticket.repository.UserTicketRepository;
import java.util.List;
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
    private final UserTicketRepository userTicketRepository;


    public void issue(Long ticketId, String email) {
        Long add = ticketRedisRepository.addKeyToSet(ticketId + ":" + email);
        if (add != 1L) {
            return;
        }

        int amount = getAmount(ticketId);
        Long increment = ticketRedisRepository.increment();

        if (amount < increment) {
            return;
        }

        // RDB 쓰기 연산 (Kafka 메세지 발행으로 바꿀 예정)

        userTicketRepository.save(new UserTicket(ticketId, email));
//        ticket.issue();
//        ticketRepository.saveAndFlush(ticket);
//        publisher.publishEvent(new TicketEvent(ticketId, email));
    }

    public void issue2(Long ticketId, String email) {
        int amount = getAmount(ticketId);
        List<Long> execute = ticketRedisRepository.execute(new RedisVO("ticket:" + ticketId, email));

        if (execute.get(0) >= amount) {
            return;
        }

        if (execute.get(1) != 1L) {
            return;
        }

        // RDB 쓰기 연산 (Kafka 메세지 발행으로 바꿀 예정)

        userTicketRepository.save(new UserTicket(ticketId, email));
//        ticket.issue();
//        ticketRepository.saveAndFlush(ticket);
//        publisher.publishEvent(new TicketEvent(ticketId, email));
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

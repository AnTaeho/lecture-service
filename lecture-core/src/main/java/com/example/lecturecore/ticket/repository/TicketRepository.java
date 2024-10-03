package com.example.lecturecore.ticket.repository;

import com.example.lecturecore.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select t.amount from Ticket t where t.id = :ticketId")
    Integer getTicketAmount(@Param("ticketId") Long ticketId);

}

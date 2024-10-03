package com.example.lecturecore.ticket.repository;

import com.example.lecturecore.ticket.domain.TicketOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketOutboxRepository extends JpaRepository<TicketOutbox, Long> {
}

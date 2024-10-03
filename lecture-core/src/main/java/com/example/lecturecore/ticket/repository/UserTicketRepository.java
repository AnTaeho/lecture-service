package com.example.lecturecore.ticket.repository;

import com.example.lecturecore.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
}

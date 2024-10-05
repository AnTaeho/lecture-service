package com.example.redissubscriber.userticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
}

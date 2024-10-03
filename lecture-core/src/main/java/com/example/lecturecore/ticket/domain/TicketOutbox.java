package com.example.lecturecore.ticket.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketOutbox {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_outbox_id")
    private Long id;

    private Long ticketId;
    private String email;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    public TicketOutbox(Long ticketId, String email) {
        this.ticketId = ticketId;
        this.email = email;
        this.status = OutboxStatus.PUBLISHED;
    }
}

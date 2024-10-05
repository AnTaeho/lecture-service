package com.example.kafkaconsumer.dto;

public record TicketEvent (
        Long ticketId,
        String email
) {
}

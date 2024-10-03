package com.example.lecturecore.event.ticket;

public record TicketEvent (
        Long ticketId,
        String email
) {
}

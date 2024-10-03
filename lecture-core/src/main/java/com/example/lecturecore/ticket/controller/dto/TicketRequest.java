package com.example.lecturecore.ticket.controller.dto;

public record TicketRequest(
        Long lectureId,
        int amount
) {
}

package com.example.lecturecore.ticket.controller;

import com.example.lecturecore.common.dto.CommonResponse;
import com.example.lecturecore.common.dto.EmptyDto;
import com.example.lecturecore.ticket.controller.dto.TickerResponse;
import com.example.lecturecore.ticket.controller.dto.TicketRequest;
import com.example.lecturecore.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public CommonResponse<TickerResponse> createTicket(@RequestBody TicketRequest ticketRequest) {
        return new CommonResponse<>(ticketService.createTicket(ticketRequest));
    }

    @PostMapping("/{ticketId}")
    public CommonResponse<EmptyDto> issueTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.issue(ticketId, getUserName());
        return CommonResponse.EMPTY;
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

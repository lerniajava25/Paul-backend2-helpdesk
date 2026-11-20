package com.example.demo.controller;

import com.example.demo.model.TicketStatus;
import com.example.demo.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/agent/tickets")
public class AgentTicketController {

    private final TicketService ticketService;

    public AgentTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{id}/assign")
    public String assignTicket(@PathVariable Long id, Principal principal) {
        ticketService.assignToMe(id, principal.getName());
        return "redirect:/tickets/" + id;
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam TicketStatus status) {
        ticketService.updateStatus(id, status);
        return "redirect:/tickets/" + id;
    }
}

package com.example.demo.controller;

import com.example.demo.dto.CreateTicketForm;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public String tickets(Principal principal, Model model) {
        List<Ticket> tickets = ticketService.getTicketsFor(principal.getName());
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/tickets/new")
    public String newTicket(Model model) {
        model.addAttribute("form", new CreateTicketForm());
        return "tickets-new";
    }

    @PostMapping("/tickets")
    public String createTicket(@Valid @ModelAttribute("form")  CreateTicketForm form,
                               BindingResult result,
                               Principal principal) {
        if (result.hasErrors()) {
            return "tickets-new";
        }

        ticketService.createTicket(form, principal.getName());
        return "redirect:/tickets";
    }
}

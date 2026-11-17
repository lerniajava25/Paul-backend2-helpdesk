package com.example.demo.service;

import com.example.demo.dto.CreateTicketForm;
import com.example.demo.model.Role;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getTicketsFor (String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        if (user.getRole() == Role.USER) {
            return ticketRepository.findByCreatorId(user.getId());
        }

        return ticketRepository.findAll();
    }

    public Ticket createTicket (CreateTicketForm form, String username) {
        User user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        Ticket ticket = new Ticket();
        ticket.setTitle(form.getTitle());
        ticket.setDescription(form.getDescription());

        ticket.setCreator(user);

        return  ticketRepository.save(ticket);
    }
}

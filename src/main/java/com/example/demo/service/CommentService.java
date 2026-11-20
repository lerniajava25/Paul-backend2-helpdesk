package com.example.demo.service;

import com.example.demo.dto.CreateCommentForm;
import com.example.demo.model.Comment;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TicketService ticketService;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          TicketService ticketService,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.ticketService = ticketService;
        this.userRepository = userRepository;
    }

    public List<Comment> getComments(Ticket ticket) {
        return commentRepository.findByTicketOrderByCreatedAtAsc(ticket);
    }

    public void addComment(Long ticketId, CreateCommentForm form, String username) {
        Ticket ticket = ticketService.getTicket(ticketId, username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        Comment comment = new Comment();
        comment.setContent(form.getContent());
        comment.setAuthor(user);
        comment.setTicket(ticket);

        commentRepository.save(comment);
    }
}

package com.matthewmcroberts.supportdesk.service;

import com.matthewmcroberts.supportdesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    // Creation
    // createTicket


    // Retrieval
    // getTicket
    // getAllTickets
    // getTicketsCreatedByUser
    // getAssignedTickets

    // Assignment
    // assignTicket
    // unassignTicket
    // claimTicket

    // Ticket updates
    // updateTicket
    // updateStatus
    // updatePriority
    // updateCategory

    // Workflow
    // resolveTicket
    // closeTicket
    // reopenTicket

    // Deletion
    // deleteTicket
}

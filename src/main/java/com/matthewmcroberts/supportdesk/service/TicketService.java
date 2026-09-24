package com.matthewmcroberts.supportdesk.service;

import com.matthewmcroberts.supportdesk.dto.request.CreateTicketRequestDto;
import com.matthewmcroberts.supportdesk.dto.response.TicketResponseDto;
import com.matthewmcroberts.supportdesk.mapper.TicketMapper;
import com.matthewmcroberts.supportdesk.model.Category;
import com.matthewmcroberts.supportdesk.model.Ticket;
import com.matthewmcroberts.supportdesk.model.User;
import com.matthewmcroberts.supportdesk.repository.CategoryRepository;
import com.matthewmcroberts.supportdesk.repository.TicketRepository;
import com.matthewmcroberts.supportdesk.repository.UserRepository;
import com.matthewmcroberts.supportdesk.types.Status;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TicketResponseDto createTicket(@NonNull final CreateTicketRequestDto dto, @NonNull final String email) {
        final Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        final Ticket ticket = Ticket.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(Status.OPEN)
                .priority(dto.getPriority())
                .category(category)
                .createdBy(user)
                .assignedTo(null)
                .build();

        final Ticket saved = ticketRepository.save(ticket);

        return TicketMapper.ticketModelToTicketResponseDto(saved);
    }

    public TicketResponseDto getTicket(final long id) {
        final Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return TicketMapper.ticketModelToTicketResponseDto(ticket);
    }

    public List<TicketResponseDto> getAllTickets() {
        final List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream().map(TicketMapper::ticketModelToTicketResponseDto).toList();
    }

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

package com.matthewmcroberts.supportdesk.mapper;

import com.matthewmcroberts.supportdesk.dto.response.TicketResponseDto;
import com.matthewmcroberts.supportdesk.model.Ticket;
import lombok.NonNull;

public class TicketMapper {
    public static TicketResponseDto ticketModelToTicketResponseDto(@NonNull final Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus().name())
                .priority(ticket.getPriority().name())
                .categoryId(ticket.getCategory().getId())
                .createdById(ticket.getCreatedBy().getId())
                .assignedToId(ticket.getAssignedTo() != null ? ticket.getAssignedTo().getId() : null)
                .createdAt(ticket.getCreatedAt().toString())
                .updatedAt(ticket.getUpdatedAt().toString())
                .build();
    }
}

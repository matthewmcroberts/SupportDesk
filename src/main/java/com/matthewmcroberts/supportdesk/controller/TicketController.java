package com.matthewmcroberts.supportdesk.controller;

import com.matthewmcroberts.supportdesk.dto.request.CreateTicketRequestDto;
import com.matthewmcroberts.supportdesk.dto.response.TicketResponseDto;
import com.matthewmcroberts.supportdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    public final TicketService ticketService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TicketResponseDto> createTicket(@RequestBody final CreateTicketRequestDto createTicketRequestDto,
                                                          @AuthenticationPrincipal final UserDetails userDetails) {
        final TicketResponseDto dto = ticketService.createTicket(createTicketRequestDto, userDetails.getUsername());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TicketResponseDto> getTicket(@PathVariable final String id) {
        final TicketResponseDto dto = this.ticketService.getTicket(Long.parseLong(id));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        final List<TicketResponseDto> dto = this.ticketService.getAllTickets();
        return ResponseEntity.ok().body(dto);
    }
}

package com.matthewmcroberts.supportdesk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {
    private long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private long categoryId;
    private long createdById;
    private Long assignedToId;
    private String createdAt;
    private String updatedAt;
}

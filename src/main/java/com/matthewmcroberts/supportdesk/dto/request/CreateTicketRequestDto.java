package com.matthewmcroberts.supportdesk.dto.request;

import com.matthewmcroberts.supportdesk.types.Priority;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequestDto {
    private String title;
    private String description;
    private Priority priority;
    private long categoryId;
}

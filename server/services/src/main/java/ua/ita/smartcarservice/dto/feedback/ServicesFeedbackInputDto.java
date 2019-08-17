package ua.ita.smartcarservice.dto.feedback;

import lombok.Data;

@Data
public class ServicesFeedbackInputDto {
    String text;
    String userName;
    Long serviceId;
}

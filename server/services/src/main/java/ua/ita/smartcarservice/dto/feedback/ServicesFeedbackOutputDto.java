package ua.ita.smartcarservice.dto.feedback;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServicesFeedbackOutputDto {
    private Long id;

    private String text;

    private String time;

    private String fullName;

    private String serviceName;

    public ServicesFeedbackOutputDto(Long id, String text, String time, String fullName, String serviceName) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.fullName = fullName;
        this.serviceName = serviceName;
    }
}

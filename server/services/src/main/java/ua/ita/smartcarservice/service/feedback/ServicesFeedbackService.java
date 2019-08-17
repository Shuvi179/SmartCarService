package ua.ita.smartcarservice.service.feedback;

import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackOutputDto;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackInputDto;

import java.util.List;

public interface ServicesFeedbackService {
    void addFeedbackToService(ServicesFeedbackInputDto servicesFeedbackInputDto);

    List<ServicesFeedbackOutputDto> getServicesFeedback(Long serviceId);

    List<ServicesFeedbackOutputDto> getUsersFeedback(String username);

    Double getServicesRating(Long serviceId);
}


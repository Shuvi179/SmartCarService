package ua.ita.smartcarservice.service.feedback;

import java.util.List;

public interface FeedbackToLeaveService {
    void saveFeedbackToLeave(String username, List<Long> workerId);

    List<Long> findWorkersIdToLeaveFeedbackByUsername(String username);

    void deleteByUsername(String username);
}

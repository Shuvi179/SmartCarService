package ua.ita.smartcarservice.service.feedback;

public interface WorkersRatingsService {
    void addRatingToWorker(Long workerId, Integer rate);
    Double getAvgWorkersRating(Long workerId);
}

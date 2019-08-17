package ua.ita.smartcarservice.service.impl.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.FeedbackToLeave;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.feedback.FeedbackToLeaveRepository;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.feedback.FeedbackToLeaveService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackToLeaveServiceImpl implements FeedbackToLeaveService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackToLeaveRepository feedbackToLeaveRepository;

    @Override
    public void saveFeedbackToLeave(String username, List<Long> workerId) {
        UserEntity user;

        user = userService.findUser(username);
        workerId.parallelStream().forEach(eachWorkerId -> {
            UserEntity worker;
            worker = userService.findById(eachWorkerId);
            feedbackToLeaveRepository.save(new FeedbackToLeave(user, worker));
        });
    }

    @Override
    public List<Long> findWorkersIdToLeaveFeedbackByUsername(String username) {
        List<FeedbackToLeave> feedbackToLeaveList;
        List<Long> workersIdList;
        UserEntity userEntity;

        workersIdList = new ArrayList<>();
        userEntity = userRepository.getByUsername(username);
        feedbackToLeaveList = feedbackToLeaveRepository.findAllByUserId(userEntity);

        feedbackToLeaveList.parallelStream().forEach(each -> {
            workersIdList.add(each.getWorkerId().getId());
        });

        return workersIdList;
    }

    @Override
    public void deleteByUsername(String username) {
        UserEntity userEntity;

        userEntity = userRepository.getByUsername(username);
        this.feedbackToLeaveRepository.deleteAllByUserId(userEntity);
    }
}

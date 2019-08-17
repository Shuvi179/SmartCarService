package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.entity.Roles;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkersSkillRepository;
import ua.ita.smartcarservice.service.technicalservice.SkillService;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    WorkersSkillRepository workersSkillRepository;

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    UserTechnicalServiceRepository userTechnicalServiceRepository;

    @Autowired
    SkillService skillService;

    @Override
    public UserEntity getWorkerById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void saveWorker(UserEntity workerEntity, Long skillId, TechnicalServiceEntity technicalServiceEntity) {
        UserEntity createdWorkerEntity = userRepository.save(workerEntity);
        SkillEntity skillEntity = skillRepository.getOne(skillId);

        workersSkillRepository.save(new WorkersSkill(createdWorkerEntity, skillEntity));
        userTechnicalServiceRepository.save(new UserTechnicalService(workerEntity, technicalServiceEntity));
    }

    @Override
    public List<UserEntity> getAllWorkers() {
        return userRepository.getUserEntitiesByRoleName(Roles.ROLE_WORKER.toString());
    }

    @Override
    public void updateWorker(UserEntity workerEntity, SkillEntity skillEntity) {
        userRepository.save(workerEntity);
        workersSkillRepository.save(new WorkersSkill(workerEntity, skillEntity));
    }

    @Override
    public void deleteWorker(Long workerId) throws Exception {
        UserEntity workerEntity;

        workerEntity = userRepository.getUserById(workerId);
        userRepository.delete(workerEntity);
    }

    @Override
    public List<WorkerDto> findByCarIdAndWorkersSkill(String name, Long carId) {
        return userRepository.findByCarIdAndWorkersSkill(name, carId)
                .stream().map(this::getWorkerDto).collect(Collectors.toList());
    }

    @Override
    public List<WorkerSkillDto> addSkillToWorkersList(List<UserEntity> workersList) {
        List<WorkerSkillDto> workerSkillDtoList = new ArrayList<>();
        workersList.parallelStream().forEach(worker -> {
            workerSkillDtoList.add(getWorkerSkillDto(worker,
                    workersSkillRepository.getByWorkerId(worker).getSkill()/*getSkillByWorkerId(userRepository.getUserById(worker.getId()))*/));
        });

        return workerSkillDtoList;
    }

    @Override
    public WorkerSkillDto getWorkerSkillDtoById(Long workerId) {
        WorkerSkillDto workerSkillDto;
        UserEntity workersEntity;

        workersEntity = userRepository.getUserById(workerId);
        workerSkillDto = getWorkerSkillDto(
                workersEntity,
                workersSkillRepository.getByWorkerId(workersEntity).getSkill());

        return workerSkillDto;
    }

    private WorkerSkillDto getWorkerSkillDto(UserEntity workerEntity, SkillEntity skillEntity) {
        WorkerSkillDto workerSkillDto = new WorkerSkillDto();

        workerSkillDto.setId(workerEntity.getId());
        workerSkillDto.setEmail(workerEntity.getEmail());
        workerSkillDto.setFullName(workerEntity.getFullName());
        workerSkillDto.setNumberPhone(workerEntity.getNumberPhone());
        workerSkillDto.setUserName(workerEntity.getUsername());
        workerSkillDto.setPassword(workerEntity.getPassword());
        workerSkillDto.setSkill(skillService.getSkillDto(skillEntity));

        return workerSkillDto;
    }

    private WorkerDto getWorkerDto(UserEntity worker) {
        WorkerDto workerDto = new WorkerDto();

        workerDto.setWorkerId(worker.getId());
        workerDto.setFullName(worker.getFullName());

        return workerDto;
    }

    public void addSkillToWorker(String username, Long skillId) {
        SkillEntity skillEntity = skillRepository.getOne(skillId);
        UserEntity workerEntity = userRepository.getByUsername(username);

        workersSkillRepository.save(new WorkersSkill(workerEntity, skillEntity));
    }


}

package ua.ita.smartcarservice.controller.technicalservice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.booking.WorkerBySkillNameDto;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.booking.WorkerWithSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;
import ua.ita.smartcarservice.service.feedback.WorkersRatingsService;
import ua.ita.smartcarservice.service.impl.booking.WorkInfo;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class WorkerController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private WorkersRatingsService workersRatingsService;

    @Autowired
    private WorkDependencyService workDependencyService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
     * Method get data about chooses works and find optimal required time and workers schedule
     *
     * @param workerWithSkillDto - info about chooses works
     * @return dto object with contains info about workers
     */
    @PostMapping("/workers/skill")
    public ResponseEntity<WorkerBySkillNameDto> findAllByCarAndSto(@RequestBody WorkerWithSkillDto
                                                                           workerWithSkillDto) {

        WorkInfo workInfo = workDependencyService.findWorkInfo(workerWithSkillDto.getWorksName());

        Map<String, List<WorkerDto>> workersBySkillName = workerWithSkillDto.getSkillsName().stream().collect(
                Collectors.toMap(s -> s,
                        s -> workerService.findByCarIdAndWorkersSkill(s, workerWithSkillDto.getCarId()))
        );

        WorkerBySkillNameDto workerBySkillNameDto = new WorkerBySkillNameDto();
        workerBySkillNameDto.setWorkersList(workersBySkillName);
        workerBySkillNameDto.setRequiredTime(workInfo.getRequiredTime());
        workerBySkillNameDto.setWorksInfo(workInfo.getWorkInfo());

        return new ResponseEntity<>(workerBySkillNameDto, HttpStatus.OK);
    }

    /**
     * Method for deleting worker by id
     *
     * @param id
     * @return ResponseEntity with status
     */
    @DeleteMapping("/workers/{id}")
    public ResponseEntity deleteWorkerById(@PathVariable Long id) {
        ResponseEntity responseEntity;

        try {
            logger.info("Trying to delete worker by id: " + id);
            this.workerService.deleteWorker(id);
            logger.info("Successfully deleted worker by id: " + id);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            logger.error("Can not delete worker by id: " + id + " Ex:" + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method for giving a rating for Worker by if
     *
     * @param id
     * @param rate
     */
    @PostMapping("/workers/{id}/rating/{rate}")
    public void addRatingToWorker(@PathVariable Long id, @PathVariable Integer rate) {
        try {
            workersRatingsService.addRatingToWorker(id, rate);
            logger.info("Rating: " + rate + " added ro worker id:" + id);
        } catch (Exception e) {
            logger.error("Error while adding a rating to worker: " + id +
                    " Details: " + e);
        }
    }

    /**
     * Method returns average rating of Worker by id
     *
     * @param id
     * @return rating
     */
    @GetMapping("/workers/{id}/rating")
    public ResponseEntity<Double> getWorkersRating(@PathVariable Long id) {
        ResponseEntity<Double> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(workersRatingsService.getAvgWorkersRating(id), HttpStatus.OK);
            logger.info("Successfully got rating of worker id:" + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting a rating if worker id:" + id +
                    " Details: " + e);
        }

        return responseEntity;
    }

    /**
     * Method returns DTO of Worker with Skill by its id
     *
     * @param id
     * @return WorkerWithSkillDto
     */
    @GetMapping("/workers/{id}")
    public ResponseEntity<WorkerSkillDto> getWorkerById(@PathVariable Long id) {
        ResponseEntity<WorkerSkillDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(workerService.getWorkerSkillDtoById(id), HttpStatus.OK);
            logger.info("Successfully get a Worker with skill DTO by id:" + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting a Worker with skill DTO by id:" + id +
                    " Details: " + e);
        }

        return responseEntity;
    }
}


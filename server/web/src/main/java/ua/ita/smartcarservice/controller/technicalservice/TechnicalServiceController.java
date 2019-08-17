package ua.ita.smartcarservice.controller.technicalservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackInputDto;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackOutputDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Controller (REST) works with TechnicalService
 */
@RequestMapping("/api")
@RestController
public class TechnicalServiceController {

    @Autowired
    ServicesFeedbackService servicesFeedbackService;

    @Autowired
    TechnicalServiceService technicalServiceService;

    @Autowired
    UserService userService;

    @Autowired
    WorkerService workerService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /*
    Method for getting all the technical services
     */
    @GetMapping("/techservices")
    ResponseEntity<List<TechnicalServiceDto>> getAllTechnicalServices() {
        ResponseEntity<List<TechnicalServiceDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(technicalServiceService.getAllTechnicalServicesDto(), HttpStatus.OK);
            logger.info("Successfully get all the technical services.");
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting all the technical services. Details: {}", e);
        }

        return responseEntity;
    }

    /*
    Method for creating TechnicalService, it gets parameters from the URL
     */
    @PostMapping("/users/{id}/techservices")
    @ResponseBody
    void createTechnicalService(
            @PathVariable Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address) {

        try {
            technicalServiceService.createTechnicalService(name, address, id);
            logger.info("Successfully created a technical service.");
        } catch (Exception e) {
            logger.error("Error while creating a technical service. Details: " + e.getMessage());
        }
    }

    /*
    Method for getting whole information about about current TechnicalService by its id
     */
    @GetMapping("/techservices/{id}")
    ResponseEntity<TechnicalServiceDto> getTechnicalService(@PathVariable Long id) {
        ResponseEntity<TechnicalServiceDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    technicalServiceService.getTechnicalServiceDtoById(id), HttpStatus.OK);
            logger.info("Successfully get a technical service by id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting a technical service by id: " + id + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method fot creation a connection between Worker and TechnicalService
     * also it adds a Skill to Current Worker
     */
    @PostMapping("/techservices/{id}/workers/{username}/skills/{skillId}")
    ResponseEntity addWorkerToTechnicalService(@PathVariable Long id, @PathVariable String username, @PathVariable Long skillId) {
        ResponseEntity responseEntity;

        try {
            /*  Adding Worker to Technical Service    */
            technicalServiceService.addUserToTechnicalService(username, id);

            /* Adding Skill to Worker */
            workerService.addSkillToWorker(username, skillId);

            responseEntity = new ResponseEntity(HttpStatus.OK);
            logger.info("Successfully get a technical service`s workers by id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            logger.error("Error while connection a technical service and worker. id: " + id +
                    " Worker`s username^ " + username + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method fot creation a connection between User and TechnicalService
     */
    @PostMapping("/techservices/{id}/users/{username}")
    ResponseEntity applyUserToTechnicalService(@PathVariable Long id, @PathVariable String username) {
        ResponseEntity responseEntity;

        try {
            /*  Adding Worker to Technical Service    */
            technicalServiceService.addUserToTechnicalService(username, id);

            responseEntity = new ResponseEntity(HttpStatus.OK);
            logger.info("Successfully applied a user username: " + username + " a technical service with id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            logger.error("Error while aworkerspplying a technical service id:" + id +
                    " and user username: " + username +
                    " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     Method for getting Workers from current TechnicalService,
     it gets parameters from the URL
     */
    @GetMapping("/techservices/{id}/workers")
    ResponseEntity<List<WorkerSkillDto>> getTechnicalServiceWorkers(@PathVariable Long id) {

        ResponseEntity<List<WorkerSkillDto>> responseEntity;

        try {
            List<UserEntity> workersList =
                    technicalServiceService.getUsersByRoleAndTechnicalSevice("ROLE_WORKER", id);
            List<WorkerSkillDto> workersWithSkillList =
                    workerService.addSkillToWorkersList(workersList);

            responseEntity = new ResponseEntity<List<WorkerSkillDto>>(
                    workersWithSkillList,
                    HttpStatus.OK);
            logger.info("Successfully get a technical service`s workers by id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting a technical service`s workers by id: " + id + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /*
    Method for updating information about current TechnicalService,
    it gets parameters from the URL
     */
    @PutMapping("/techservices/{id}")
    void updateTechnicalService(@PathVariable Long id,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "address", required = false) String address) {
        try {
            TechnicalServiceEntity technicalServiceEntity = technicalServiceService.getTechnicalServiceById(id);

            if (name != null) {
                technicalServiceEntity.setName(name);
            }

            if (address != null) {
                technicalServiceEntity.setAddress(address);
            }

            technicalServiceService.updateTechnicalService(technicalServiceEntity);

            logger.info("Successfully updated a technical service by id: " + id);
        } catch (Exception e) {
            logger.error("Error while updating a technical service by id: " + id + " Details: " + e.getMessage());
        }
    }

    /*
    Method for getting Technical Service bu User ID
     */
    @GetMapping("/users/{userId}/techservice")
    public ResponseEntity<TechnicalServiceDto> findTechnicalServiceByUserId(@PathVariable("userId") Long userId) {

        ResponseEntity<TechnicalServiceDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<TechnicalServiceDto>(technicalServiceService.getTechnicalServiceDtoByUser(userId), HttpStatus.OK);
            logger.info("Successfully get a Technical Service by User`s id: " + userId);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<TechnicalServiceDto>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting a Technical Service by User`s id: " + userId + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /*
        Method for getting Technical Service bu User ID
    */
    @GetMapping("/users/username/{username}/techservice")
    public ResponseEntity<TechnicalServiceDto> findTechnicalServiceByUsername(@PathVariable("username") String username) {

        ResponseEntity<TechnicalServiceDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<TechnicalServiceDto>(technicalServiceService.getTechnicalServiceDtoByUser(username), HttpStatus.OK);
            logger.info("Successfully get a Technical Service by username: " + username);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<TechnicalServiceDto>(HttpStatus.NO_CONTENT);
            logger.error("Error while getting a Technical Service by username id: " + username + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /*
    Method for deleting Technical Service by ID
     */
    @DeleteMapping("/techservices/{techServiceId}")
    void deleteTechnicalService(@PathVariable Long techServiceId) throws Exception {
        try {
            technicalServiceService.deleteTechnicalService(techServiceId);
            logger.info("Successfully deleted a Technical Service by id: " + techServiceId);
        } catch (Exception e) {
            logger.error("Error while deleting a Technical Service by id: " + techServiceId + " Details: " + e.getMessage());
        }
    }

    @GetMapping("/techservices/{techServiceId}/rating")
    Double getServicesRating(@PathVariable Long techServiceId) {
        return servicesFeedbackService.getServicesRating(techServiceId);
    }

    @PostMapping("/techservices/{techServiceId}/feedback")
    void addFeedbackAboutTechnicalService(
            @PathVariable Long techServiceId,
            @RequestBody ServicesFeedbackInputDto servicesFeedbackInputDto) {

        servicesFeedbackService.addFeedbackToService(servicesFeedbackInputDto);
        System.out.println(servicesFeedbackInputDto);
        logger.info(servicesFeedbackInputDto);
    }

    @GetMapping("/techservices/{techServiceId}/feedback")
    ResponseEntity<List<ServicesFeedbackOutputDto>> getAllFeedbackAboutService(@PathVariable Long techServiceId) {
        ResponseEntity<List<ServicesFeedbackOutputDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    servicesFeedbackService.getServicesFeedback(techServiceId), HttpStatus.OK);
            logger.info("Successfully got feedback about Technical Service by id: " + techServiceId);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.warn("Can not get feedback about Technical Service by id: " + techServiceId + " Details: " + e.getMessage());
        }

        return responseEntity;
    }
}

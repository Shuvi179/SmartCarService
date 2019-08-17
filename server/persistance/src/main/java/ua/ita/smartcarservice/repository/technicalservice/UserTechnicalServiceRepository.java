package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;

@Repository
public interface UserTechnicalServiceRepository extends JpaRepository<UserTechnicalService, Long> {

    @Query(value = "select u from UserEntity as u " +
            "left join UserTechnicalService as us " +
            "where us.technicalServiceId = :technicalServiceId ")
    UserEntity getTechnicalServiceManagerByServiceId(@Param("technicalServiceId") TechnicalServiceEntity technicalServiceId);

    UserTechnicalService getByTechnicalServiceId(TechnicalServiceEntity technicalServiceEntity);

    UserTechnicalService getById(Long id);

    void deleteByTechnicalServiceId(Long id);

    @Query("select ut from UserTechnicalService as ut " +
            "left join Car as c on ut.userId = c.user " +
            "where c.id = :id")
    UserTechnicalService findTechnicalServiceByCarId(@Param("id") Long id);
}

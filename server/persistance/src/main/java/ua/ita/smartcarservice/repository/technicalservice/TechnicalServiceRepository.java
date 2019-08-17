package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

@Repository
public interface TechnicalServiceRepository extends JpaRepository<TechnicalServiceEntity, Long> {
    TechnicalServiceEntity getByTechnicalServiceId(Long id);

    @Query(value = "select ts.technical_service_id, ts.name, ts.address, ts.dealer_id from technical_services as ts " +
            "left join users_service as us on ts.technical_service_id = us.technical_service_id " +
            "left join user as u on us.user_id = u.id where u.id = :id", nativeQuery = true)
    TechnicalServiceEntity getTechnicalServiceEntityByUser(@Param("id") Long id);

    @Query(value = "select ts.technical_service_id, ts.name, ts.address, ts.dealer_id from technical_services as ts " +
            "left join users_service as us on ts.technical_service_id = us.technical_service_id " +
            "left join user as u on us.user_id = u.id where u.username = :username", nativeQuery = true)
    TechnicalServiceEntity getTechnicalServiceEntityByUsername(@Param("username") String id);

    List<TechnicalServiceEntity> findAllByDealer_UserEntity_Username(String username);
}

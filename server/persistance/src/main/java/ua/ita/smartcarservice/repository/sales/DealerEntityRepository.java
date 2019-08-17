package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

/**
 * Created by 1 on 27.02.2019.
 */
public interface DealerEntityRepository extends JpaRepository<DealerEntity,Long> {

    DealerEntity findByUserEntity_Username(String username);

    DealerEntity findByDealerEdr(String edr);

//    List<Car> findAllCarByDealer(DealerEntity dealerEntity);



}
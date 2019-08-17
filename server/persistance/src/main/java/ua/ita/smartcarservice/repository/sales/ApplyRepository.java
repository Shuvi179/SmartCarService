package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.Apply;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

/**
 * Created by 1 on 03.03.2019.
 */
public interface ApplyRepository  extends JpaRepository<Apply,Long> {

    List<Apply> findAllByDealer(DealerEntity dealerEntity);
    List<Apply> findAllByDealer_UserEntityUsername(String username);

    Apply findByDealerAndAndTechnicalService(DealerEntity dealerEntity, TechnicalServiceEntity technicalServiceEntity);


}

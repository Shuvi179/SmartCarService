package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.SaleManagerEntity;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

/**
 * Created by 1 on 27.02.2019.
 */
public interface UserSaleManagerRepository extends JpaRepository<UserSaleManager,Long> {

    //    findBySaleManagerEntity(SaleManagerEntity saleManagerEntity) {
    UserSaleManager findBySaleManagerEntity(SaleManagerEntity saleManagerEntity);

}

package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.dto.sales.ApplyToDealerDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;

import java.util.List;


public interface ApplyService {

    void createApplyToDealer(ApplyToDealerDto applyToDealerDto);

    void deleteApply(ApplyToDealerDto applyToDealerDto);

    void applyToDealer(ApplyToDealerDto applyToDealerDto);

    List<TechnicalServiceDto>TECHNICAL_SERVICE_DTOS(String username);

}

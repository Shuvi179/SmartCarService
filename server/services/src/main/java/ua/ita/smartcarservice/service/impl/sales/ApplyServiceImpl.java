package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.ApplyToDealerDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sales.Apply;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.repository.sales.ApplyRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.sales.ApplyService;

import java.util.ArrayList;
import java.util.List;

@Service

public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private DealerEntityRepository dealerRepository;

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    ApplyRepository applyRepository;

    @Override
    public void createApplyToDealer(ApplyToDealerDto applyToDealerDto) {
        if(applyRepository.findByDealerAndAndTechnicalService(dealerRepository.findByDealerEdr(applyToDealerDto.getDealerEdr()),technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId()))==null){
            applyRepository.save(new Apply(dealerRepository.findByDealerEdr(applyToDealerDto.getDealerEdr()),technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId())));
        }

    }

    @Override
    public void deleteApply(ApplyToDealerDto applyToDealerDto) {
applyRepository.delete(applyRepository.findByDealerAndAndTechnicalService(dealerRepository.findByUserEntity_Username(applyToDealerDto.getDealerEdr()),technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId())));
    }


    @Override
    public void applyToDealer(ApplyToDealerDto applyToDealerDto) {

        DealerEntity dealerEntity=dealerRepository.findByUserEntity_Username(applyToDealerDto.getDealerEdr());
        TechnicalServiceEntity technicalServiceEntity=technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId());
        technicalServiceEntity.setDealer(dealerEntity);
        technicalServiceRepository.save(technicalServiceEntity);
        deleteApply(applyToDealerDto);
    }

    public TechnicalServiceDto convertToDto(TechnicalServiceEntity technicalServiceEntity) {
        TechnicalServiceDto dto = new TechnicalServiceDto();

        dto.setStoId(technicalServiceEntity.getTechnicalServiceId());
        dto.setName(technicalServiceEntity.getName());
        dto.setAddress(technicalServiceEntity.getAddress());
        //dto.setTechnicalManager(userTechnicalServiceRepository.getTechnicalServiceManagerByServiceId(technicalServiceEntity));

        return dto;
    }
    @Override
    public List<TechnicalServiceDto> TECHNICAL_SERVICE_DTOS(String username) {
List<TechnicalServiceDto>technicalServiceDtos=new ArrayList<>();
        List<Apply>applies=applyRepository.findAllByDealer_UserEntityUsername(username);

        for (Apply apply:applies){
            technicalServiceDtos.add(convertToDto(technicalServiceRepository.getByTechnicalServiceId(apply.getTechnicalService().getTechnicalServiceId())));
        }
        return technicalServiceDtos;
    }
}

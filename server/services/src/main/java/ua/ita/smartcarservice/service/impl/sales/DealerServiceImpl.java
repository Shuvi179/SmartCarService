package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.DealerCarDto;
import ua.ita.smartcarservice.dto.sales.DealerDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.service.sales.DealerService;

import java.util.ArrayList;
import java.util.List;


@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealerEntityRepository dealerRepository;

    @Autowired
    private CarRepository carRepository;
//    @Override
//    public void createDealer(DealerEntity dealerEntity, String username) {
//        dealerRepository.save(new DealerEntity(userRepository.getUserById(userRepository.findByUsername(username).get().getId()),dealerEntity.getDealerName(),dealerEntity.getDealerAddress(),dealerEntity.getDealerEdr(),dealerEntity.getDealerEmail()));
//    }


    @Override
    public void createDealer(DealerDto dealerDto, String username) {
        dealerRepository.save(new DealerEntity(userRepository.getByUsername(username),dealerDto.getDealerName(),dealerDto.getDealerAddress(),dealerDto.getDealerEdr(),dealerDto.getDealerEmail()));
    }

    @Override
    public void createDealer(DealerEntity dealerEntity) {
        dealerRepository.save(dealerEntity);
    }

    @Override
    public DealerEntity getDealerEntityByUserName(String username) {
        return dealerRepository.findByUserEntity_Username(username);
    }

    @Override
    public List<DealerEntity> getAllDealers() {
        return dealerRepository.findAll();
    }

    @Override
    public DealerDto getDealerDtoByUserName(String username) {
        DealerEntity dealerEntity=dealerRepository.findByUserEntity_Username(username);
        return new DealerDto(dealerEntity.getDealerName(),dealerEntity.getDealerAddress(),dealerEntity.getDealerEdr(),dealerEntity.getDealerEmail());
    }

    @Override
    public DealerEntity dealerDtoToEntity(DealerDto dealerDto) {
        return dealerRepository.findByDealerEdr(dealerDto.getDealerEdr());
    }

    @Override
    public void editDealerEntityByEntity(DealerEntity dealerEntity) {
        dealerRepository.save(dealerEntity);
    }

    @Override
    public void editDealerByDealerDto(DealerDto dealerDto) {
        DealerEntity dealerEntity=dealerRepository.findByDealerEdr(dealerDto.getDealerEdr());
        dealerEntity.setDealerName(dealerDto.getDealerName());
        dealerEntity.setDealerAddress(dealerDto.getDealerAddress());
        dealerEntity.setDealerEmail(dealerDto.getDealerEmail());
        dealerRepository.save(dealerEntity);
    }

    @Override
    public List<DealerDto> getAllDealerDto() {
        List<DealerEntity>dealerEntities=dealerRepository.findAll();
        List<DealerDto>dealerDtos=new ArrayList<>();
        for(DealerEntity dealerEntity:dealerEntities){
            dealerDtos.add(new DealerDto(dealerEntity.getDealerName(),dealerEntity.getDealerAddress(),dealerEntity.getDealerEdr(),dealerEntity.getDealerEmail()));
        }
        return dealerDtos;
    }

    @Override
    public List<DealerCarDto> getAllCarDtoByUserNameDealer(String username) {
        List<DealerCarDto> dealerCarDtos=new ArrayList<>();
//        List<Car>cars=dealerRepository.findAllCarByDealer(dealerRepository.findByUserEntity_Username(username));
        List<Car>cars=carRepository.findAllByDealer(dealerRepository.findByUserEntity_Username(username));
        for (Car car:cars){
            dealerCarDtos.add(new DealerCarDto(car.getBrand(),
                    car.getModel(),
                    car.getGraduation_year(),
                    car.getNumber(),
                    car.getPrice(),
                    car.getVin(),
                    car.getEnd_guarantee(),
                    car.getDealer().getDealerName()));
        }
        return dealerCarDtos;
    }
}


package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.CreateTraidInDto;
import ua.ita.smartcarservice.dto.sales.TradeInDto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.repository.sales.TradeInRepository;
import ua.ita.smartcarservice.service.sales.TradeInService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TradeInServiceImpl implements TradeInService {

    @Autowired
    private DealerEntityRepository dealerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private TradeInRepository tradeInRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createTradeIn(CreateTraidInDto traidInDto) {

        tradeInRepository.save(new TradeIn(traidInDto.getVinNewCar(),
                traidInDto.getVinUsedCar(),
                userRepository.findByUsername(traidInDto.getUsername()).get().getId(),
                dealerRepository.findByDealerEdr(traidInDto.getDealerEdr()).getDealerId(),
                "active"
               ));
    }

    @Override
    public void create(String vinNewCar, String vinUsedCar) {

        tradeInRepository.save(new TradeIn(vinNewCar,vinUsedCar,carRepository.findByVin(vinUsedCar).getUser().getId(),carRepository.findByVin(vinNewCar).getDealer().getDealerId(),"active",carRepository.findByVin(vinNewCar).getDealer()));

    }

    @Override
    public List<TradeIn> tradesIn(String username) {
        return tradeInRepository.findAllByDealerAndIsactive(dealerRepository.findByUserEntity_Username(username),"active");
    }

    @Override
    public List<TradeInDto> tradeinDtos(String username) {
        List<TradeInDto> tradeInDtos = new ArrayList<>();

        List<TradeIn> tradeIns = tradesIn(username);
        for (TradeIn tradeIn:tradeIns) {
       tradeInDtos.add(new TradeInDto(tradeIn.getId(),
               userRepository.getUserById(tradeIn.getIdUser()).getFullName(),
               userRepository.getUserById(tradeIn.getIdUser()).getEmail(),
               userRepository.getUserById(tradeIn.getIdUser()).getNumberPhone(),
               carRepository.findByVin(tradeIn.getVinNewCar()).getModel(),
               carRepository.findByVin(tradeIn.getVinNewCar()).getBrand(),
               carRepository.findByVin(tradeIn.getVinNewCar()).getPrice(),
               carRepository.findByVin(tradeIn.getVinUsedCar()).getModel(),
               carRepository.findByVin(tradeIn.getVinUsedCar()).getBrand()
                             ));
        }

        return tradeInDtos;
    }

    @Override
    public void deleteTradeIn(Long id) {
        tradeInRepository.deleteById(id);
    }

    @Override
    public void successTradeIn(Long id) {
        TradeIn tradeIn=tradeInRepository.getOne(id);
        tradeIn.setIsactive("notActive");
        tradeInRepository.save(tradeIn);
    }
}


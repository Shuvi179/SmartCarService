package ua.ita.smartcarservice.service.impl.technicalservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;

//@TestExecutionListeners(MockitoTestExecutionListener.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TechnicalServiceServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock//Bean
    TechnicalServiceRepository technicalServiceRepository;

    @Mock
    ServicesFeedbackService servicesFeedbackService;

    @InjectMocks
    TechnicalServiceServiceImpl technicalServiceServiceImpl = new TechnicalServiceServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTechnicalServiceEntityByUser() {
        TechnicalServiceEntity expectedEntity;
        TechnicalServiceEntity actualEntity;

        Long userId = 1L;

        expectedEntity = new TechnicalServiceEntity("ServiceName", "Address", null);

        Mockito.when(technicalServiceRepository.getTechnicalServiceEntityByUser(anyLong())).thenReturn(expectedEntity);

        actualEntity = technicalServiceRepository.getTechnicalServiceEntityByUser(userId);

        Assert.assertEquals(expectedEntity, actualEntity);
        Assert.assertEquals(expectedEntity.getTechnicalServiceId(), actualEntity.getTechnicalServiceId());
        Assert.assertEquals(expectedEntity.getName(), actualEntity.getName());
        Assert.assertEquals(expectedEntity.getAddress(), actualEntity.getAddress());
    }


    @Test
    public void getTechnicalServiceEntityDtoByUser() {
        //Preconditions
       /* TechnicalServiceDto expectedDto;
        TechnicalServiceDto actualDto;
        TechnicalServiceEntity expectedEntity;
        Long userId = 1L;

        expectedEntity = new TechnicalServiceEntity();
        expectedEntity.setTechnicalServiceId(1L);
        expectedEntity.setName("ServiceName");
        expectedEntity.setAddress("Address");

        expectedDto = new TechnicalServiceDto();
        expectedDto.setStoId(1L);
        expectedDto.setName("ServiceName");
        expectedDto.setAddress("Address");
        expectedDto.setRating(4.6D);
        expectedDto.setWorkers(new ArrayList<UserEntity>() {});

        Mockito.when(technicalServiceRepository.getTechnicalServiceEntityByUser(anyLong())).thenReturn(expectedEntity);
        Mockito.when(servicesFeedbackService.getServicesRating(expectedEntity.getTechnicalServiceId())).thenReturn(anyDouble());

        actualDto = technicalServiceServiceImpl.getTechnicalServiceDtoByUser(userId);

        Assert.assertEquals(expectedDto.getStoId(), actualDto.getStoId());
        Assert.assertEquals(expectedDto.getName(), actualDto.getName());
        Assert.assertEquals(expectedDto.getAddress(), actualDto.getAddress());*/
    }

}
package ua.ita.smartcarservice.dto.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import java.util.List;

@Data
public class TechnicalServiceDto {

    private Long stoId;

    private String name;

    private String address;

    private Double rating;

    private UserEntity technicalManager;

    private List<UserEntity> workers;
}

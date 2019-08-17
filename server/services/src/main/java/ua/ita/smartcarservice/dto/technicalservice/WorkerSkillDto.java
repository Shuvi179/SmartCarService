package ua.ita.smartcarservice.dto.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

@Data
public class WorkerSkillDto {
    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private String email;
    private String numberPhone;
    private SkillDto skill;
}

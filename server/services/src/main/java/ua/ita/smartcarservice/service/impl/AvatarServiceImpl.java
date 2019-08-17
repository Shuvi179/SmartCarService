package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.AvatarDto;
import ua.ita.smartcarservice.entity.AvatarEntity;
import ua.ita.smartcarservice.repository.AvatarRepository;
import ua.ita.smartcarservice.service.AvatarService;
import ua.ita.smartcarservice.service.UserService;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    UserService userService;

    @Override
    public void addAvatarToUser(AvatarDto avatarDto) {
        avatarRepository.save(dtoToEntity(avatarDto));
    }

    public AvatarEntity dtoToEntity(AvatarDto avatarDto) {
        return new AvatarEntity(
                userService.findById(avatarDto.getUserId()),
                avatarDto.getFileUrl(),
                avatarDto.getFilePath());
    }

    public AvatarDto entityToDto(AvatarEntity avatarEntity) {
        return new AvatarDto(
                avatarEntity.getUserId().getId(),
                avatarEntity.getFileUrl(),
                avatarEntity.getFilePath());
    }

    @Override
    public AvatarDto getAvatarDtoByUserId(Long userId) {
        return entityToDto(avatarRepository.getByUserId(userId));
    }
}

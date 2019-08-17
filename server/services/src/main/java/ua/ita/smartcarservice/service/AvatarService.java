package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.AvatarDto;

public interface AvatarService {
    void addAvatarToUser(AvatarDto avatarDto);
    AvatarDto getAvatarDtoByUserId(Long userId);

}

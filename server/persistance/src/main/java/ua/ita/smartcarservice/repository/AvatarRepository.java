package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.AvatarEntity;

@Repository
public interface AvatarRepository extends JpaRepository<AvatarEntity, Long> {
    AvatarEntity getByUserId(Long id);
}

package com.nlitvins.social_media.outbound.repository.jpa;

import com.nlitvins.social_media.outbound.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByIdIn(Set<Integer> userIds);
}

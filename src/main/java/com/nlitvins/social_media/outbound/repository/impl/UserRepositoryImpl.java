package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.domain.repository.UserRepository;
import com.nlitvins.social_media.outbound.model.UserEntity;
import com.nlitvins.social_media.outbound.repository.jpa.UserJpaRepository;
import com.nlitvins.social_media.outbound.utils.OutboundMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<User> findAll(){
        List<UserEntity> userEntities = jpaRepository.findAll();
        return OutboundMapper.Users.toDomainList(userEntities);
    }

    @Override
    public User findById(int id){
        UserEntity userEntity = jpaRepository.getReferenceById(id);
        return OutboundMapper.Users.toDomain(userEntity);
    }

    @Override
    public User save(User user){
        UserEntity userEntity = OutboundMapper.Users.toEntity(user);
        UserEntity savedUserEntity = jpaRepository.save(userEntity);
        return OutboundMapper.Users.toDomain(savedUserEntity);
    }
}

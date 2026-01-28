package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.outbound.model.PostEntity;
import com.nlitvins.social_media.outbound.repository.jpa.PostJpaRepository;
import com.nlitvins.social_media.outbound.utils.OutboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository jpaRepository;

    @Override
    public List<Post> findAll() {
        List<PostEntity> postEntities = jpaRepository.findAll();
        return OutboundMapper.Posts.toDomainList(postEntities);
    }

    @Override
    public Post findById(int id) {
        Optional<PostEntity> postEntity = jpaRepository.findById(id);
        return postEntity
                .map(OutboundMapper.Posts::toDomain)
                .orElse(null);
    }

    @Override
    public Post save(Post post) {
        PostEntity postEntity = OutboundMapper.Posts.toEntity(post);
        PostEntity savedPostEntity = jpaRepository.save(postEntity);
        return OutboundMapper.Posts.toDomain(savedPostEntity);
    }

    @Override
    public List<Post> findByAuthorIdIn(List<Integer> authorId){
        List<PostEntity> postEntity = jpaRepository.findByAuthorIdIn(authorId);
        return OutboundMapper.Posts.toDomainList(postEntity);
    }
}

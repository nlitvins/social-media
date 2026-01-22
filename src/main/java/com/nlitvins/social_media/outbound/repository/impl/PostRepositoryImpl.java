package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.repository.PostRepository;
import com.nlitvins.social_media.outbound.model.PostEntity;
import com.nlitvins.social_media.outbound.repository.jpa.PostJpaRepository;
import com.nlitvins.social_media.outbound.utils.OutboundMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository jpaRepository;

    public PostRepositoryImpl(PostJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Post> findAll(){
        List<PostEntity> postEntities = jpaRepository.findAll();
        return OutboundMapper.Posts.toDomainList(postEntities);
    }

    @Override
    public Post findById(int id){
        PostEntity postEntity = jpaRepository.getReferenceById(id);
        return OutboundMapper.Posts.toDomain(postEntity);
    }

    @Override
    public Post save(Post post){
        PostEntity postEntity = OutboundMapper.Posts.toEntity(post);
        PostEntity savedPostEntity = jpaRepository.save(postEntity);
        return OutboundMapper.Posts.toDomain(savedPostEntity);
    }


}

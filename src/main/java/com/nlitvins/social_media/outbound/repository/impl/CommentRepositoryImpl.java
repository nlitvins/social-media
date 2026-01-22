package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import com.nlitvins.social_media.outbound.model.CommentEntity;
import com.nlitvins.social_media.outbound.repository.jpa.CommentJpaRepository;
import com.nlitvins.social_media.outbound.utils.OutboundMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository jpaRepository;

    public CommentRepositoryImpl(CommentJpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Comment> findAll(){
        List<CommentEntity> commentEntities = jpaRepository.findAll();
        return OutboundMapper.Comments.toDomainList(commentEntities);
    }

    @Override
    public Comment findById(int id){
        CommentEntity commentEntity = jpaRepository.getReferenceById(id);
        return OutboundMapper.Comments.toDomain(commentEntity);
    }

    @Override
    public Comment save(Comment comment){
        CommentEntity commentEntity = OutboundMapper.Comments.toEntity(comment);
        CommentEntity savedCommentEntity = jpaRepository.save(commentEntity);
        return OutboundMapper.Comments.toDomain(savedCommentEntity);
    }
}

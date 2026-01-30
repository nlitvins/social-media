package com.nlitvins.social_media.outbound.repository.impl;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.repository.CommentRepository;
import com.nlitvins.social_media.outbound.model.CommentEntity;
import com.nlitvins.social_media.outbound.repository.jpa.CommentJpaRepository;
import com.nlitvins.social_media.outbound.utils.OutboundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository jpaRepository;

    @Override
    public List<Comment> findAll() {
        List<CommentEntity> commentEntities = jpaRepository.findAll();
        return OutboundMapper.Comments.toDomainList(commentEntities);
    }

    @Override
    public Comment findById(int id) {
        Optional<CommentEntity> commentEntity = jpaRepository.findById(id);
        return commentEntity
                .map(OutboundMapper.Comments::toDomain)
                .orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = OutboundMapper.Comments.toEntity(comment);
        CommentEntity savedCommentEntity = jpaRepository.save(commentEntity);
        return OutboundMapper.Comments.toDomain(savedCommentEntity);
    }

    @Override
    public List<Comment> findByPostIds(Set<Integer> postIds) {
        List<CommentEntity> commentEntity = jpaRepository.findByPostIdIn(postIds);
        return OutboundMapper.Comments.toDomainList(commentEntity);
    }

    @Override
    public List<Comment> findByUserIds(Set<Integer> userIds) {
        List<CommentEntity> commentEntity = jpaRepository.findByAuthorIdIn(userIds);
        return OutboundMapper.Comments.toDomainList(commentEntity);
    }
}

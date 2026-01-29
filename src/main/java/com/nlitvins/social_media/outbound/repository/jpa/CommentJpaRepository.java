package com.nlitvins.social_media.outbound.repository.jpa;

import com.nlitvins.social_media.outbound.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findByPostIdIn(Set<Integer> postIds);

    List<CommentEntity> findByAuthorIdIn(Set<Integer> userIds);
}

package com.nlitvins.social_media.outbound.repository.jpa;

import com.nlitvins.social_media.outbound.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {



}

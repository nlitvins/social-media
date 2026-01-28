package com.nlitvins.social_media.outbound.repository.jpa;

import com.nlitvins.social_media.inbound.model.UserResponse;
import com.nlitvins.social_media.outbound.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findByAuthorId(int authorId);

    List<PostEntity> findByAuthorIdIn(List<Integer> authorIds);
}

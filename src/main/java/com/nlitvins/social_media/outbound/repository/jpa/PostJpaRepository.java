package com.nlitvins.social_media.outbound.repository.jpa;

import com.nlitvins.social_media.outbound.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Integer> {

    PostEntity findByAuthorId(int authorId);
}

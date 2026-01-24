package com.nlitvins.social_media.outbound.utils;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.outbound.model.CommentEntity;
import com.nlitvins.social_media.outbound.model.PostEntity;
import com.nlitvins.social_media.outbound.model.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OutboundMapper {

    @UtilityClass
    public static class Comments {

        public static CommentEntity toEntity(Comment comment) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setId(comment.getId());
            commentEntity.setAuthorId(comment.getAuthorId());
            commentEntity.setPostId(comment.getPostId());
            commentEntity.setContent(comment.getContent());
            commentEntity.setCreationTime(comment.getCreationTime());
            return commentEntity;
        }

        public static Comment toDomain(CommentEntity commentEntity) {
            return Comment.builder()
                    .id(commentEntity.getId())
                    .authorId(commentEntity.getAuthorId())
                    .postId(commentEntity.getPostId())
                    .content(commentEntity.getContent())
                    .creationTime(commentEntity.getCreationTime())
                    .build();
        }

        public List<Comment> toDomainList(List<CommentEntity> commentEntities) {
            List<Comment> comments = new ArrayList<>();
            for (int index = commentEntities.size() - 1; index >= 0; index--) {
                CommentEntity get = commentEntities.get(index);
                comments.add(toDomain(get));
            }
            return comments;
        }
    }

    @UtilityClass
    public static class Posts {

        public static PostEntity toEntity(Post post) {
            PostEntity postEntity = new PostEntity();
            postEntity.setId(post.getId());
            postEntity.setAuthorId(post.getAuthorId());
            postEntity.setContent(post.getContent());
            postEntity.setCreationTime(post.getCreationTime());
            return postEntity;
        }

        public static Post toDomain(PostEntity postEntity) {
            return Post.builder()
                    .id(postEntity.getId())
                    .authorId(postEntity.getAuthorId())
                    .content(postEntity.getContent())
                    .creationTime(postEntity.getCreationTime())
                    .build();
        }

        public List<Post> toDomainList(List<PostEntity> postEntities) {
            List<Post> posts = new ArrayList<>();
            for (int index = postEntities.size() - 1; index >= 0; index--) {
                PostEntity get = postEntities.get(index);
                posts.add(toDomain(get));
            }
            return posts;
        }
    }

    @UtilityClass
    public static class Users{
        public static UserEntity toEntity(User user){
            UserEntity userEntity = new UserEntity();
            userEntity.setId(user.getId());
            userEntity.setUsername(user.getUserName());
            return userEntity;
        }

        public static User toDomain(UserEntity userEntity){
            return User.builder()
                    .id(userEntity.getId())
                    .userName(userEntity.getUsername())
                    .build();
        }

        public static List<User> toDomainList(List<UserEntity> userEntities){
            List<User> users = new ArrayList<>();
            for (int index = userEntities.size() - 1; index >= 0; index--){
                UserEntity get = userEntities.get(index);
                users.add(toDomain(get));
            }
            return  users;
        }
    }
}

package com.nlitvins.social_media.inbound.utils;

import com.nlitvins.social_media.domain.model.Comment;
import com.nlitvins.social_media.domain.model.Post;
import com.nlitvins.social_media.domain.model.User;
import com.nlitvins.social_media.inbound.model.CommentCreateRequest;
import com.nlitvins.social_media.inbound.model.CommentResponse;
import com.nlitvins.social_media.inbound.model.PostCreateRequest;
import com.nlitvins.social_media.inbound.model.PostResponse;
import com.nlitvins.social_media.inbound.model.UserCreateRequest;
import com.nlitvins.social_media.inbound.model.UserResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class InboundMapper {

    @UtilityClass
    public static class Comments {

        public static CommentResponse toDTO(Comment comment) {
            return CommentResponse.builder()
                    .id(comment.getId())
                    .authorId(comment.getAuthorId())
                    .postId(comment.getPostId())
                    .content(comment.getContent())
                    .creationTime(comment.getCreationTime())
                    .build();
        }

        public static List<CommentResponse> toDTOList(List<Comment> comments) {
            List<CommentResponse> commentResponses = new ArrayList<>();
            for (int index = comments.size() - 1; index >= 0; index--) {
                Comment get = comments.get(index);
                CommentResponse mapper = toDTO(get);
                commentResponses.add(mapper);
            }
            return commentResponses;
        }


        public static Comment toDomain(CommentCreateRequest request) {
            return Comment.builder()
                    .authorId(request.getAuthorId())
                    .postId(request.getPostId())
                    .content(request.getContent())
                    .creationTime(request.getCreationTime())
                    .build();
        }
    }

    @UtilityClass
    public static class Posts {

        public static PostResponse toDTO(Post post) {
            return PostResponse.builder()
                    .id(post.getId())
                    .authorId(post.getAuthorId())
                    .content(post.getContent())
                    .creationTime(post.getCreationTime())
                    .build();
        }

        public static List<PostResponse> toDTOList(List<Post> posts) {
            List<PostResponse> postResponses = new ArrayList<>();
            for (int index = posts.size() - 1; index >= 0; index--) {
                Post get = posts.get(index);
                PostResponse mapper = toDTO(get);
                postResponses.add(mapper);
            }
            return postResponses;
        }

        public static Post toDomain(PostCreateRequest request) {
            return Post.builder()
                    .authorId(request.getAuthorId())
                    .content(request.getContent())
                    .creationTime(request.getCreationTime())
                    .build();
        }
    }

    @UtilityClass
    public static class Users {

        public static UserResponse toDTO(User user) {
            return UserResponse.builder()
                    .id(user.getId())
                    .userName(user.getUserName())
                    .build();
        }

        public static List<UserResponse> toDTOList(List<User> users) {
            List<UserResponse> userResponses = new ArrayList<>();
            for (int index = users.size() - 1; index >= 0; index--) {
                User get = users.get(index);
                UserResponse mapper = toDTO(get);
                userResponses.add(mapper);
            }
            return userResponses;
        }

        public static User toDomain(UserCreateRequest request) {
            return User.builder()
                    .userName(request.getUserName())
                    .build();
        }
    }
}

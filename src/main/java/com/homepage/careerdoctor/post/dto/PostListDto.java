package com.homepage.careerdoctor.post.dto;

import com.homepage.careerdoctor.domain.Liked;
import com.homepage.careerdoctor.domain.Scrap;
import com.homepage.careerdoctor.domain.Vote;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class PostListDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostResponse {
        private Long postId;
        private String userId;
        private String postTitle;
        private String postContent;
        private LocalDateTime createdAt;
        private List<Vote> vote;
        private int likeCount;
        private int scrapCount;
    }

    @Getter @Setter
    @Builder
    @NoArgsConstructor
    public static class SearchPosts {
        private List<PostResponse> posts;

        public SearchPosts(List<PostResponse> posts) {
            this.posts = posts;
        }
    }

}

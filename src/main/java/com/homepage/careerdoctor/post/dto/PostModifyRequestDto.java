package com.homepage.careerdoctor.post.dto;

import lombok.*;

import java.time.LocalDateTime;

public class PostModifyRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Req {

        private String postTitle;
        private String postContent;

    }

    // 수정 api Response
    // 게시글 수정 : updatedAt
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ModifyPost {
        private LocalDateTime updatedAt;
        public ModifyPost(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

}

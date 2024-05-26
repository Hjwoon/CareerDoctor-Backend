package com.homepage.careerdoctor.specReport.dto;

import com.homepage.careerdoctor.domain.Gender;
import com.homepage.careerdoctor.domain.Level;
import com.homepage.careerdoctor.domain.Need;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReportWantUserListDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        @NotEmpty
        private String userId; // 소견서 작성 원하는 사람
        private String birth;
        private Level level;
        private Gender gender;


    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    public static class SearchUsers {
        private List<ReportWantUserListDto.UserResponse> users;

        public SearchUsers(List<ReportWantUserListDto.UserResponse> users) {
            this.users = users;
        }
    }

}

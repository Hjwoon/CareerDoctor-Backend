package com.homepage.careerdoctor.post.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteCountResponseDto {

    private int voteCount;
    private double percent;

}

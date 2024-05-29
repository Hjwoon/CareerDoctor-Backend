package com.homepage.careerdoctor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "VOTE")
public class Vote extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTE_ID")
    private Long voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    @JsonBackReference
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private User user;

    @Column(name = "VOTE_CONTENT")
    private String voteContent;

    @Column(name = "VOTE_COUNT")
    private int voteCount = 0;

    @Column(name = "PERCENT")
    private double percent = 0;

    public void changePost(Post post) {
        this.post = post;
        if (!post.getVotes().contains(this)) {
            post.getVotes().add(this);
        }
    }

    public void changePercent(double percent) {
        this.percent = percent;
    }

    public void changeVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}

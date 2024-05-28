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
    private int voteCount;

    public void changePost(Post post) {
        this.post = post;
        if (!post.getVotes().contains(this)) {
            post.getVotes().add(this);
        }
    }

}

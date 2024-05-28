package com.homepage.careerdoctor.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.homepage.careerdoctor.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST")
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @OneToMany(mappedBy = "post")
    private List<Liked> likeds;

    @OneToMany(mappedBy = "post")
    private List<Scrap> scraps;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Vote> votes;

    // User와의 관계 정의
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private User user;

    public void changeVote(List<Vote> votes) {
        this.votes = votes;
        for (Vote vote : votes) {
            vote.changePost(this); // 양방향 관계 설정
        }
    }

    // 게시글 제목을 수정하는 메소드
    public void changeTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    // 게시물 내용을 수정하는 메소드
    public void changeContent(String postContent) {
        this.postContent = postContent;
    }
}

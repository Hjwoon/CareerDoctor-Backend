package com.homepage.careerdoctor.post.repostitory;

import com.homepage.careerdoctor.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
}

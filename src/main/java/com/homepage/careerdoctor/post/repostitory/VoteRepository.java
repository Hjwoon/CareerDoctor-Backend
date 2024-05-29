package com.homepage.careerdoctor.post.repostitory;

import com.homepage.careerdoctor.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}

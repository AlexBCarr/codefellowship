package com.codefellowship.codefellowship.repos;

import com.codefellowship.codefellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
}

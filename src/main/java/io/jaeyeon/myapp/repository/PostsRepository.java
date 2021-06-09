package io.jaeyeon.myapp.repository;

import io.jaeyeon.myapp.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}

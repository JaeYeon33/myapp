package io.jaeyeon.myapp.model;

import io.jaeyeon.myapp.repository.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostsTest {

    @Autowired PostsRepository postsRepository;

    @Test
    public void testEntity() throws Exception {
        Posts posts = new Posts("JPA", "Content", "CHO");
        Posts savedPosts = postsRepository.save(posts);

        Posts findPosts = postsRepository.findById(savedPosts.getId()).get();

        assertThat(findPosts.getId()).isEqualTo(posts.getId());
        assertThat(findPosts.getTitle()).isEqualTo(posts.getTitle());
        assertThat(findPosts.getContent()).isEqualTo(posts.getContent());
        assertThat(findPosts.getAuthor()).isEqualTo(posts.getAuthor());
        assertThat(findPosts).isEqualTo(posts);

    }

    @Test
    public void basicCRUD() throws Exception {

        Posts posts1 = new Posts("JPA1", "Content1", "CHO");
        Posts posts2 = new Posts("JPA2", "Content2", "CHO");
        postsRepository.save(posts1);
        postsRepository.save(posts2);

        // 단건 조회 검사
        Posts findPosts1 = postsRepository.findById(posts1.getId()).get();
        Posts findPosts2 = postsRepository.findById(posts2.getId()).get();
        assertThat(findPosts1).isEqualTo(posts1);
        assertThat(findPosts2).isEqualTo(posts2);

        // 리스트 조회 검사
        List<Posts> all = postsRepository.findAll();
        assertThat(all.size()).isEqualTo(2);


        // 카운트 검증
        long count = postsRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        postsRepository.delete(posts1);
        postsRepository.delete(posts2);

        long deletedCount = postsRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

}
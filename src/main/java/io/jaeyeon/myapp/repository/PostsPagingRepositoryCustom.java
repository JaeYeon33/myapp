package io.jaeyeon.myapp.repository;

import io.jaeyeon.myapp.model.Posts;
import io.jaeyeon.myapp.model.PostsSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsPagingRepositoryCustom {

    Page<Posts> search(Pageable pageable, PostsSearch postsSearch);
}

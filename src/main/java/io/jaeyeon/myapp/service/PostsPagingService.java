package io.jaeyeon.myapp.service;

import io.jaeyeon.myapp.dto.PostsListResponseDto;
import io.jaeyeon.myapp.model.Posts;
import io.jaeyeon.myapp.repository.PostsPagingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsPagingService {

    private final PostsPagingRepository postsPagingRepository;

    @Transactional(readOnly = true)
    public Page<PostsListResponseDto> findAllDesc(Pageable pageable, String title) {
        Page<Posts> posts;
        if ("".equals(title)) {
            posts = postsPagingRepository.findAllDesc(pageable);
        } else {
            // 정렬순서 변경
            Pageable p = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
            posts = postsPagingRepository.findByTitle(p, title);
        }
        List<PostsListResponseDto> results = posts.getContent().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        long totalCount = posts.getTotalElements();

        return new PageImpl<>(results, pageable, totalCount);
    }
}

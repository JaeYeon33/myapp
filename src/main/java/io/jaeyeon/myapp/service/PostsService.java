package io.jaeyeon.myapp.service;

import io.jaeyeon.myapp.dto.PostsListResponseDto;
import io.jaeyeon.myapp.dto.PostsResponseDto;
import io.jaeyeon.myapp.dto.PostsSaveRequestDto;
import io.jaeyeon.myapp.dto.PostsUpdateRequestDto;
import io.jaeyeon.myapp.model.Posts;
import io.jaeyeon.myapp.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts posts = getOne(id);

        posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = getOne(id);

        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = getOne(id);
        postsRepository.delete(posts);
    }

    private Posts getOne(Long id) throws IllegalStateException {
        return postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
    }

}

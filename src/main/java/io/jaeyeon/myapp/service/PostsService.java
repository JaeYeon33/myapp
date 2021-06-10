package io.jaeyeon.myapp.service;

import io.jaeyeon.myapp.dto.PostsResponseDto;
import io.jaeyeon.myapp.dto.PostsSaveRequestDto;
import io.jaeyeon.myapp.dto.PostsUpdateRequestDto;
import io.jaeyeon.myapp.model.Posts;
import io.jaeyeon.myapp.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    }

}

package io.jaeyeon.myapp.dto;

import io.jaeyeon.myapp.model.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}

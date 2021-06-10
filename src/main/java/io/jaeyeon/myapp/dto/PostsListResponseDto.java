package io.jaeyeon.myapp.dto;

import io.jaeyeon.myapp.model.Posts;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto implements Serializable {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public String getLink() {
        return "/posts/update/" + id;
    }

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}

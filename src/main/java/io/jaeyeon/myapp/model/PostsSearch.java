package io.jaeyeon.myapp.model;

import lombok.Getter;

@Getter
public class PostsSearch {

    private String title;
    private String author;
    private boolean orderBy;

    public PostsSearch(String title, String author, boolean orderBy) {
        this.title = title;
        this.author = author;
        this.orderBy = orderBy;
    }
}

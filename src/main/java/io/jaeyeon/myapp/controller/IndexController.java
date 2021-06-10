package io.jaeyeon.myapp.controller;

import io.jaeyeon.myapp.model.Posts;
import io.jaeyeon.myapp.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsRepository postsRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping
    public String Posts(Model model) {
        List<Posts> all = postsRepository.findAll();
        model.addAttribute("alls", all);
        return "index";
    }
}

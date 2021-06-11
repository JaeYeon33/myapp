package io.jaeyeon.myapp.controller;


import io.jaeyeon.myapp.dto.PostsResponseDto;
import io.jaeyeon.myapp.service.PostsPagingService;
import io.jaeyeon.myapp.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final PostsPagingService postsPagingService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault Pageable pageable,
                        @RequestParam(value = "title", required = false, defaultValue = "") String title) {
        //model.addAttribute("posts", postsService.findAllDesc());

        // 페이지당 표시 게시글 수를 3개로 제한
        pageable = PageRequest.of(pageable.getPageNumber(), 3);
//        model.addAttribute("posts", postsPagingService.findAllDesc(pageable));
        model.addAttribute("posts", postsPagingService.findAllDesc(pageable, title));
        model.addAttribute("title", title);

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}

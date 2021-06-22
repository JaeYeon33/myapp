package io.jaeyeon.myapp.controller;


import io.jaeyeon.myapp.config.dto.LoginUser;
import io.jaeyeon.myapp.config.dto.SessionUser;
import io.jaeyeon.myapp.dto.PostsResponseDto;
import io.jaeyeon.myapp.dto.PostsSaveRequestDto;
import io.jaeyeon.myapp.service.PostsPagingService;
import io.jaeyeon.myapp.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // Model 객체
    // 서버 탬플릿 엔진에서 사용가능한 객체를 저장할 수 있다.
    // 해당 메소드에서는 postsService.findAllDesc()로 가져온 결과를 posts라는 이름으로 index.mustache에 전달한다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // 세션에 저장된 값이 있을 경우만 model에 userName으로 등록한다.
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }


    @PostMapping("/posts/save")
    public String postsWrite(PostsSaveRequestDto requestDto) {
        postsService.save(requestDto);
        return "redirect:/";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}

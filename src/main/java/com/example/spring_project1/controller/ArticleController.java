package com.example.spring_project1.controller;

import com.example.spring_project1.dto.ArticleForm;
import com.example.spring_project1.entity.Article;
import com.example.spring_project1.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString()); --> 로깅으로 대체
      log.info(form.toString());

        // 1. DTO를 변솬 -> Entity
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(article.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 가져온 데이터를 모델에 등록
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 뷰페이지 설정
        return "articles/index"; // articles/index.mustache
    }

}

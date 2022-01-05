package com.example.spring_project1.controller;

import com.example.spring_project1.dto.ArticleForm;
import com.example.spring_project1.entity.Article;
import com.example.spring_project1.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        // DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 엔티티를 DB로 저장한다
        // DB에서 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 기존 데이터에 값을 갱신
        if(target != null){
            articleRepository.save(articleEntity);
        }

        // 수정 결과 페이지로 리다이렉트 한다
        return "redirect:/articles/" + articleEntity.getId();

    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청");

        // 삭제 요청을 가져온다
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 대상을 삭제한다
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다!");
        }

        // 결과페이지로 리다이렉트
        return "redirect:/articles";
    }



}

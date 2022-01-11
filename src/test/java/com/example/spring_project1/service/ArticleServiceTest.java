package com.example.spring_project1.service;

import com.example.spring_project1.dto.ArticleForm;
import com.example.spring_project1.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스트링부트와 연동되어 테스팅
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "aaaa", "1111");
        Article b = new Article(2L, "bbbb", "2222");
        Article c = new Article(3L, "cccc", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 예상
            Long id = 1L;
            Article expected = new Article(id, "aaaa", "1111");

        // 실제
            Article article = articleService.show(id);

        // 비교
            assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력(){
        // 예상
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto입력() {
        // 예상
        Long id = 1L;
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto입력() {
        // 예상
        Long id = 1L;
        String title = "dddd";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, null);
        Article expected = new Article(id, title, content);

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지않는_id의_dto입력() {
        // 예상
        Long id = 4L;
        String title = "dddd";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, null);
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_실패_id만_있는_dto입력() {
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(null, null, null);
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        // 예상
        Long id = 1L;
        String title = "aaaa";
        String content = "1111";
        Article expected = new Article(id, title, content);

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지않는_id_입력() {
        // 예상
        Long id = 4L;
        Article expected = null;

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected, article);
    }
}
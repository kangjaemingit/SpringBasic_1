package com.example.spring_project1.repository;

import com.example.spring_project1.entity.Article;
import com.example.spring_project1.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(4L, "인생 영화?", "댓글ㄱ");
            Comment a = new Comment(1L, article, "kim", "아이언맨");
            Comment b = new Comment(2L, article, "kang", "아이엠 샘");
            Comment c = new Comment(3L, article, "park", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);


            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }

        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(1L, "aaaa", "1111");
            List<Comment> expected = Arrays.asList();


            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }

        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        /* Case 4: 9999번 게시글의 모든 댓글 조회 */
        /* Case 5: -1번 게시를의 모든 댓글 조회 */
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 모든 댓글 조회 */
        {
            // 입력 데이터를 준비
            String nickname = "park";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상
            Comment a = new Comment(3L, new Article(4L, "인생 영화?", "댓글ㄱ"), nickname, "쇼생크 탈출");
            Comment b = new Comment(6L, new Article(5L, "소울 푸드?", "댓글ㄱㄱ"), nickname, "초밥");
            Comment c = new Comment(9L, new Article(6L, "취미?", "댓글ㄱㄱㄱ"), nickname, "축구");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 2: "kim"의 모든 댓글 조회 */
        /* Case 3: null의 모든 댓글 조회 */
        /* Case 4: ""의 모든 댓글 조회 */
        /* Case 5: "i"의 모든 댓글 조회 */
    }
}
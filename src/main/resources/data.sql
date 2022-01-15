INSERT INTO article(id, title, content) VALUES (1, 'aaaa', '1111');
INSERT INTO article(id, title, content) VALUES (2, 'bbbb', '2222');
INSERT INTO article(id, title, content) VALUES (3, 'cccc', '3333');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (4, '인생 영화?', '댓글ㄱ');
INSERT INTO article(id, title, content) VALUES (5, '소울 푸드?', '댓글ㄱㄱ');
INSERT INTO article(id, title, content) VALUES (6, '취미?', '댓글ㄱㄱㄱ');

-- comment 더미 데이터
---- 4번 게시글 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'kim', '아이언맨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'kang', '아이엠 샘');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'park', '쇼생크 탈출');

---- 5번 게시글 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'kim', '치킨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'kang', '육회');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'park', '초밥');

---- 6번 게시글 댓글
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'kim', '게임');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'kang', '볼링');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'park', '축구');


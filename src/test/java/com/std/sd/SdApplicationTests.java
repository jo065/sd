package com.std.sd;

import com.std.sd.answer.Answer;
import com.std.sd.answer.AnswerRepository;
import com.std.sd.question.Question;
import com.std.sd.question.QuestionRepository;
import com.std.sd.question.QuestionService;
import com.std.sd.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SdApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;


    @Test
    @DisplayName("문제 생성")
    void t00() {
        Question q1 = new Question();
        q1.setCreateDate(LocalDateTime.now());
        q1.setSubject("제목입니다.");
        q1.setContent("내용입니다.");
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setCreateDate(LocalDateTime.now());
        q2.setSubject("제목입니다.2");
        q2.setContent("내용입니다.2");
        this.questionRepository.save(q2);
    }

    @Test
    @DisplayName("답변 생성")
    void t01() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("답변입니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    @DisplayName("데이터 밀어넣기")
    void testInsertJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content, null);
        }
    }
    @Test
    @DisplayName("스트림 버전 데이터 밀어넣기")
    void t02() {
        IntStream.rangeClosed(3, 300)
                .forEach(no -> questionService.create("테스트 제목입니다. %d".formatted(no), "테스트내용입니다. %d".formatted(no), null));
    }
    @Test
    @DisplayName("테스트 유저 넣기")
    void t03() {
        this.userService.create("user", "user1@test.com", "1234");
        this.userService.create("user2", "user2@test.com", "1234");
    }
}

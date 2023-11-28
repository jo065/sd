package com.std.sd;

import com.std.sd.answer.Answer;
import com.std.sd.answer.AnswerRepository;
import com.std.sd.question.Question;
import com.std.sd.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SdApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("문제 생성")
    void test00() {
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
    void test01() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("답변입니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }
}

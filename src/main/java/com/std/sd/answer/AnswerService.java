package com.std.sd.answer;

import com.std.sd.question.Question;
import com.std.sd.question.QuestionForm;
import com.std.sd.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Question question, String content) {
        Answer a = new Answer();
        a.setCreateDate(LocalDateTime.now());
        a.setQuestion(question);
        a.setContent(content);
        this.answerRepository.save(a);
    }
}

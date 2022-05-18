package edu.laverno.service;

import edu.laverno.dao.TestDAO;
import edu.laverno.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {

    private final TestDAO dao;

    @Autowired
    public TestServiceImpl(TestDAO dao) {
        this.dao = dao;
    }

    @Override
    public void startTesting() {
        int correctAnswers = 0;
        Scanner scr = new Scanner(System.in);
        String name = scr.nextLine();
        System.out.println("Привет, " + name + "!\nТест:");

        List<Question> questions = dao.getQuestions();
        for (Question q : questions) {
            System.out.println(q.toString());
            System.out.print("Ваш ответ: ");
            if (checkAnswer(q, scr.nextInt()))
                correctAnswers++;
        }

        System.out.println("Итог теста: " + correctAnswers + "/" + questions.size());
    }

    private boolean checkAnswer(Question question, Integer answer) {
        return dao.getAnswer(question).getAnswer().equals(question.getBodyOfQuestion().get(answer-1));
    }
}

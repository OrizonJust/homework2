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
    private final MessageSource messageSource;

    @Autowired
    public TestServiceImpl(TestDAO dao, MessageSource messageSource) {
        this.dao = dao;
        this.messageSource = messageSource;
    }

    @Override
    public void startTesting() {
        int correctAnswers = 0;
        Scanner scr = new Scanner(System.in);
        String name = scr.nextLine();
        System.out.println("Hello, " + name + "!\nTest:");

        List<Question> questions = dao.getQuestions();
        for (Question q : questions) {
            System.out.println(q.toString());
            System.out.print("Your answer is: ");
            if (checkAnswer(q, scr.nextInt()))
                correctAnswers++;
        }

        System.out.println("Test: " + correctAnswers + "/" + questions.size());
    }

    private boolean checkAnswer(Question question, Integer answer) {
        return dao.getAnswer(question).getAnswer().equals(question.getBodyOfQuestion().get(answer-1));
    }
}

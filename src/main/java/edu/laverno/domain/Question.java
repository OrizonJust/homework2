package edu.laverno.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

    private final String name;
    private final List<String> bodyOfQuestion;

    public Question(String... input) {
        this.name = input[0];
        this.bodyOfQuestion = new ArrayList<>();
        if (input.length > 1) {
            bodyOfQuestion.addAll(Arrays.asList(input).subList(1, input.length));
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getBodyOfQuestion() {
        return bodyOfQuestion;
    }

    @Override
    public String toString() {
        return name + ": " + bodyOfQuestion;
    }
}

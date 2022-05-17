package edu.laverno.domain;

public class Answer {

    private final String name;
    private final String answer;

    public Answer(String... data) {
        this.name = data[0];
        this.answer = data[1];
    }

    public String getName() {
        return name;
    }

    public String getAnswer() {
        return answer;
    }
}

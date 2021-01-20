package com.github.nilstrieb.util.trivia;

public class TriviaQuestion {
    private String question;
    private String[] answers;
    private int correctAnswer;
    private Arc arc;

    public TriviaQuestion(String question, int correctAnswer, Arc arc, String ... answers) {
        this.question = question;
        this.answers = answers;
        this.arc = arc;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public Arc getArc() {
        return arc;
    }
}

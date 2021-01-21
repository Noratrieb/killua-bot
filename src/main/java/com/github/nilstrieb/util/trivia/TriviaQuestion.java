package com.github.nilstrieb.util.trivia;

import java.util.Arrays;

public class TriviaQuestion {

    public static final int EXAM = 0;
    public static final int ZOLDYCK_FAMILY = 1;
    public static final int HEAVENS_ARENA = 2;
    public static final int YORKNEW_CITY = 3;
    public static final int GREED_ISLAND = 4;
    public static final int CHIMERA_ANT = 5;
    public static final int ELECTION = 6;

    private final String question;
    private final String[] answers;
    private final int correctAnswer;
    private final int arc;

    public TriviaQuestion(String question, int correctAnswer, int arc, String ... answers) {
        this.question = question;
        this.answers = answers;
        this.arc = arc;
        this.correctAnswer = correctAnswer;
    }

    public TriviaQuestion(String[] answers) {
        this.question = answers[0];
        this.answers = answers[1].split(" *; *");
        this.correctAnswer = Integer.parseInt(answers[2]);
        this.arc = Integer.parseInt(answers[3]);
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

    public int getArc() {
        return arc;
    }

    @Override
    public String toString() {
        return "TriviaQuestion{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + correctAnswer +
                ", arc=" + arc +
                '}';
    }
}

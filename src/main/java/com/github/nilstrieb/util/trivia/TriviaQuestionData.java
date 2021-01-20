package com.github.nilstrieb.util.trivia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriviaQuestionData {
    static List<List<TriviaQuestion>> questions = new ArrayList<>();
    private static final Random random = new Random();

    static {
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());


        add(new TriviaQuestion("What's the name of Gons aunt?", 1, Arc.EXAM,
                "Kite", "Mito", "Ging", "Kurapika"));
        add(new TriviaQuestion("What is Bisky's true form?", 1, Arc.GREED_ISLAND,
                "Boy", "Big Woman", "Little Girl"));
        add(new TriviaQuestion("Why does Leorio want money?", 0, Arc.EXAM,
                "To become a doctor", "To send it to e-girls", "So that others don't get it", "To buy large quantities of drugs"));
        add(new TriviaQuestion("Where did Meruem die?", 2, Arc.CHIMERA_ANT,
                "Volcano", "In the desert with Netero", "In the basement of the palace", "In the womb of the queen"));
        add(new TriviaQuestion("Who is Alluka?", 3, Arc.ELECTION,
                "Gon's sister", "A Zodiac", "The 'dark' side of Nanika", "Killua's sister"));
        add(new TriviaQuestion("How did Gon die on Greed Island?", 0, Arc.GREED_ISLAND,
                "He didn't die on Greed Island", "Hisoka killed him with a card to the neck", "He lost too muich blood when Genthru blew his arm of", "He killed himself after Killuas death"));
        add(new TriviaQuestion("How many doors are there at the Testing Gate of the Zoldyck Family?", 0, Arc.ZOLDYCK_FAMILY,
                "7", "5", "12", "8"));
        add(new TriviaQuestion("What was Gon's aim after Killua left him alone playing with the Chairman Netero?", 1, Arc.EXAM,
                "Get the ball", "Make Netero use his right hand", "Make Netero use his left leg", "Let Netero drop the ball"));
        add(new TriviaQuestion("What did Tonpa mix into the drink?", 2, Arc.EXAM,
                "Sleeping pills", "Headache tabletsschla", "Laxative", "Rat poison"));

    }

    private static void add(TriviaQuestion triviaQuestion) {
        questions.get(triviaQuestion.getArc().getNumber()).add(triviaQuestion);
    }

    public static TriviaQuestion getQuestion(int toArc) {
        int totalQuestions = 0;
        for (int i = 0; i <= toArc; i++) {
            totalQuestions += questions.get(i).size();
        }

        int randomQuestion = random.nextInt(totalQuestions);

        for (int i = 0; i <= toArc; i++) {
            if (randomQuestion >= questions.get(i).size()) {
                randomQuestion -= questions.get(i).size();
            } else {
                return questions.get(i).get(randomQuestion);
            }
        }

        throw new IndexOutOfBoundsException("No Question available for arc " + toArc);
    }
}

package com.github.nilstrieb.util.trivia;

import com.github.nilstrieb.util.ConsoleColors;
import com.google.gson.Gson;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriviaQuestionData {
    static List<List<TriviaQuestion>> questions = new ArrayList<>();
    private static final Random random = new Random();

    private static String JSON_PATH_JAR;

    static {
        try {
            JSON_PATH_JAR = new File(TriviaQuestionData.class.getProtectionDomain().getCodeSource()
                .getLocation().toURI()).getPath().replaceAll("(.*\\\\).*?\\.jar", "$1") + "trivia_questions.json";;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private static final String JSON_PATH_INTELLIJ = "trivia_questions.json";
    private static final String JSON_PATH = JSON_PATH_JAR;

    static {
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        System.out.println(ConsoleColors.BLUE + "[TriviaQuestionData 37] JSON File Path: " + new File(JSON_PATH + "hallo").getAbsolutePath() + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "[TriviaQuestionData 38] JSON File Path: " + JSON_PATH+ ConsoleColors.RESET);
        loadJSON();
    }

    private static void loadJSON() {

        System.out.println("[TriviaQuestionData 44] Load JSON File");

        StringBuilder json = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(JSON_PATH))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                json.append(line);
                line = bufferedReader.readLine();
            }


            Gson gson = new Gson();
            TriviaQuestion[] array = gson.fromJson(json.toString(), TriviaQuestion[].class);

            for (TriviaQuestion triviaQuestion : array) {
                questions.get(triviaQuestion.getArc()).add(triviaQuestion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void saveJSON(String path, TriviaQuestion[] array) {
        Gson gson = new Gson();

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(path))) {

            String json = gson.toJson(array);
            bufferedWriter.write(json);
            bufferedWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveJSONFromAll(String path) {
        TriviaQuestion[] questionsArray = new TriviaQuestion[getTotalQuestions()];

        int i = 0;
        for (List<TriviaQuestion> questionList : questions) {
            for (TriviaQuestion triviaQuestion : questionList) {
                questionsArray[i] = triviaQuestion;
                i++;
            }
        }

        saveJSON(path, questionsArray);
    }

    public static void add(TriviaQuestion triviaQuestion) {
        questions.get(triviaQuestion.getArc()).add(triviaQuestion);
        saveJSONFromAll(JSON_PATH);
    }

    public static TriviaQuestion getQuestion(int toArc) {
        int totalQuestions = getTotalQuestions(toArc);

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

    private static int getTotalQuestions(int toArc) {
        int totalQuestions = 0;
        for (int i = 0; i <= toArc; i++) {
            totalQuestions += questions.get(i).size();
        }
        return totalQuestions;
    }

    private static int getTotalQuestions() {
        return getTotalQuestions(questions.size() - 1);
    }

    public static void dump() {
        int i = 0;
        for (List<TriviaQuestion> question : questions) {
            System.out.println(ConsoleColors.BLUE_BACKGROUND + ConsoleColors.YELLOW +
                    "QUESTIONS ARC: " + i + ConsoleColors.RESET);
            for (TriviaQuestion triviaQuestion : question) {
                System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + triviaQuestion + ConsoleColors.RESET);
            }
            i++;
        }
    }
}

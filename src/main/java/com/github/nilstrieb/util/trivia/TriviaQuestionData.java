package com.github.nilstrieb.util.trivia;

import com.github.nilstrieb.util.ConsoleColors;
import com.google.gson.Gson;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriviaQuestionData {
    static List<List<TriviaQuestion>> questions = new ArrayList<>();
    private static final List<TriviaQuestion> newQuestions = new ArrayList<>();
    private static final Random random = new Random();

    static {
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        questions.add(new ArrayList<>());
        loadJSON();
    }

    private static void loadJSON() {
        StringBuilder json = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("trivia_questions.json"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                json.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        TriviaQuestion[] array = gson.fromJson(json.toString(), TriviaQuestion[].class);

        for (TriviaQuestion triviaQuestion : array) {
            questions.get(triviaQuestion.getArc()).add(triviaQuestion);
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
        saveJSONFromAll("trivia_questions.json");
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

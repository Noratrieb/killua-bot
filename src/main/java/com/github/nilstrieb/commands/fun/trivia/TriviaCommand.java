package com.github.nilstrieb.commands.fun.trivia;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.sections.Section;
import com.github.nilstrieb.util.ConsoleColors;
import com.github.nilstrieb.util.trivia.TriviaQuestion;
import com.github.nilstrieb.util.trivia.TriviaQuestionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TriviaCommand extends Command {

    public TriviaCommand() {
        super("trivia", "Answer random Trivia questions!", "trivia 0", "(maximal arc (inclusive) as a number) or (add)",
                "Answer random trivia questions by the community!\n" +
                "You can choose the last arc the questions will be from to avoid spoilers\n" +
                "Arcs:\n" +
                "0 Hunter Exam arc\n" +
                "1 Zoldyck Family arc\n" +
                "2 Heavens Arena arc\n" +
                "3 Yorknew City arc\n" +
                "4 Greed Island arc\n" +
                "5 Chimera Ant arc\n" +
                "6 Election arc\n" +
                "\n" +
                "Add questions using `" + Config.PREFIX + "trivia add`");
    }

    @Override
    public void called(String args) {

        if (args.equals("dump") && event.getAuthor().getIdLong() == Config.NILS_ID) {
            TriviaQuestionData.dump();
            reply("dumped");
        } else if (args.startsWith("add")) {
            reply("Enter the Question (Example: \"What is the name of Gons's father?\")");
            new AddSection(event.getTextChannel().getIdLong(), event.getAuthor().getIdLong());
        } else {
            int arc = 0;
            try {
                arc = Integer.parseInt(args);
            } catch (NumberFormatException ignored) {
            }

            TriviaQuestion question = TriviaQuestionData.getQuestion(arc);
            StringBuilder answers = new StringBuilder();
            for (int i = 0; i < question.getAnswers().length; i++) {
                answers.append(i).append(". ").append(question.getAnswers()[i]).append("\n");
            }
            EmbedBuilder builder = Config.getDefaultEmbed(event)
                    .addField(question.getQuestion(), answers.toString(), false);

            reply(builder.build());
            new TriviaSection(event.getTextChannel().getIdLong(), event.getAuthor().getIdLong(), question);
        }
    }

///
/// Trivia Section class
///

    private static class TriviaSection extends Section {
        private final TriviaQuestion question;

        private TriviaSection(long textChannelID, long userID, TriviaQuestion question) {
            super(textChannelID, userID);
            this.question = question;
        }

        @Override
        public void called(String text) {
            String msg = text.toLowerCase();
            String answer;

            String correctAnswer = question.getAnswers()[question.getCorrectAnswer()];
            if (correctAnswer.equals(msg) || msg.equals(question.getCorrectAnswer() + ".")) {
                answer = "Correct!";
            } else {
                try {
                    if (Integer.parseInt(msg) == question.getCorrectAnswer()) {
                        answer = "Correct!";
                    } else {
                        answer = "False!";
                    }
                } catch (NumberFormatException e) {
                    answer = "False!";
                }
            }

            EmbedBuilder builder = Config.getDefaultEmbed(event)
                    .setTitle(answer)
                    .setThumbnail(null)
                    .addField("Correct answer", correctAnswer, false);
            if (question.getArc() == TriviaQuestion.EXAM) {
                builder.setFooter("Tip: Use " + Config.PREFIX + "help trivia for more questions.");
            }
            reply(builder.build());
            dispose();
        }
    }


///
/// Add question section class
///

    private static class AddSection extends Section {

        private int status = 0;
        private static final String[] messages = {"Enter all answers seperated by a ; (Example: \"Ging;Mito;Gon\")",
                "Enter the correct answer index starting at 0 (Example: \"0\")",
                "Enter the arc this question belongs to as a number (Example: \"0\")\n" +
                "EXAM = 0\n" +
                "ZOLDYCK_FAMILY = 1\n" +
                "HEAVENS_ARENA = 2\n" +
                "YORKNEW_CITY = 3\n" +
                "GREED_ISLAND = 4\n" +
                "CHIMERA_ANT = 5\n" +
                "ELECTION = 6\n"
                };
        private final String[] answers = new String[4];

        private AddSection(long textChannelID, long userID) {
            super(textChannelID, userID);
        }

        @Override
        public void called(String text) {
            if (event.getAuthor().getIdLong() == Config.NILS_ID && text.startsWith("debug")) {
                answers[0] = "question";
                answers[1] = "a;b;c;d";
                answers[2] = "0";
                answers[3] = "0";
                new TriviaApproval(event, new TriviaQuestion(answers));
            } else {

                System.out.println(ConsoleColors.BLUE_BOLD + "[TriviaCommand.AddSection 139] Received Next Message: "
                        + text + " status: " + status + ConsoleColors.RESET);
                answers[status] = event.getMessage().getContentRaw();
                if (status >= 3) {
                    try {
                        new TriviaApproval(event, new TriviaQuestion(answers));
                        reply("Question successfully added for approval");
                    } catch (NumberFormatException e) {
                        reply("Error: " + e.getMessage());
                    }
                    dispose();
                } else {
                    reply(messages[status]);
                }
                status++;
                deleteMsg();
            }
        }
    }
}

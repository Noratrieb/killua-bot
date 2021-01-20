package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.sections.ChannelMessageEventManager;
import com.github.nilstrieb.sections.Section;
import com.github.nilstrieb.util.trivia.Arc;
import com.github.nilstrieb.util.trivia.TriviaQuestion;
import com.github.nilstrieb.util.trivia.TriviaQuestionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TriviaCommand extends Command {

    public TriviaCommand() {
        super("trivia", "Answer random Trivia questions!", "trivia 0", "(maximal arc (inclusive) as a number)", """
                Answer random trivia questions by the community!
                You can choose the last arc the questions will be from to avoid spoilers
                Arcs:
                0 Hunter Exam arc
                1 Zoldyck Family arc
                2 Heavens Arena arc
                3 Yorknew City arc
                4 Greed Island arc
                5 Chimera Ant arc
                6 Election arc
                """);
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
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

        reply(event, builder.build());
        new TriviaSection(event.getTextChannel().getIdLong(), event.getAuthor().getIdLong(), question);
    }


    private static class TriviaSection extends Section {
        private final TriviaQuestion question;

        public TriviaSection(long textChannelID, long userID, TriviaQuestion question) {
            super(textChannelID, userID);
            this.question = question;
        }

        @Override
        public void messageReceived(MessageReceivedEvent event) {
            String msg = event.getMessage().getContentRaw().toLowerCase();
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
            if (question.getArc() == Arc.EXAM) {
                builder.setFooter("Tip: Use " + Config.PREFIX + "help trivia for more questions.");
            }
            reply(event, builder.build());
            ChannelMessageEventManager.removeListener(this);
        }
    }
}

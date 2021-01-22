package com.github.nilstrieb.commands.fun.trivia;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.reactions.ReactionAdapter;
import com.github.nilstrieb.reactions.ReactionEventManager;
import com.github.nilstrieb.util.ConsoleColors;
import com.github.nilstrieb.util.trivia.TriviaQuestion;
import com.github.nilstrieb.util.trivia.TriviaQuestionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class TriviaApproval extends ReactionAdapter {

    public static final String APPROVE_EMOTE = "\u2705"; //✅
    public static final String DENY_EMOTE = "\u274C"; //❌

    private final TriviaQuestion question;

    public TriviaApproval(MessageReceivedEvent event, TriviaQuestion question) {
        this.question = question;

        EmbedBuilder builder = Config.getDefaultEmbed(event)
                .setTitle("TRIVIA QUESTION APPROVAL")
                .addField("Question:", question.getQuestion(), false);
        for (int i = 0; i < question.getAnswers().length; i++) {
            builder.addField("Answer " + i, question.getAnswers()[i], false);
        }
        builder.addField("Correct:", String.valueOf(question.getCorrectAnswer()), false)
                .addField("Arc: ", String.valueOf(question.getArc()), false);

        //send message
        event.getJDA().getGuildById(Config.KUKUROO_MOUNTAIN_ID).getTextChannelById(Config.TRIVIA_APPROVAL_CHANNEL_ID).sendMessage(builder.build()).queue(message -> {
            message.addReaction(APPROVE_EMOTE).queue();
            message.addReaction(DENY_EMOTE).queue();
            create(message.getIdLong());
            ReactionEventManager.addMessage(message.getIdLong(), this);
            System.out.println(ConsoleColors.BLUE + "[TriviaApproval 38] Trivia Question registered for approval" + ConsoleColors.RESET);
        });
    }

    @Override
    public void onReactionAdded(MessageReactionAddEvent event) {
        if (event.getUser() != null) {
            if (event.getUser().getIdLong() == Config.NILS_ID) {
                String emote = event.getReaction().getReactionEmote().getName();
                System.out.println(ConsoleColors.BLUE + "[TriviaApproval 47] Received Emote " + emote + ConsoleColors.RESET);
                if (emote.equals(APPROVE_EMOTE)) {
                    event.getTextChannel().sendMessage("Question approved.").queue();
                    System.out.println(ConsoleColors.BLUE + "[TriviaApproval 50] Question Approved: " + question.getQuestion() + ConsoleColors.RESET);
                    TriviaQuestionData.add(question);
                    dispose();
                } else if (emote.equals(DENY_EMOTE)) {
                    event.getTextChannel().sendMessage("Question denied.").queue();
                    System.out.println(ConsoleColors.BLUE + "[TriviaApproval 55] Question Denied: " + question.getQuestion() + ConsoleColors.RESET);
                    dispose();
                }
            }
        }
    }
}

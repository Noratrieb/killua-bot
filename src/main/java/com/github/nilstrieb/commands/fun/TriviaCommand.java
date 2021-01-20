package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.sections.ChannelMessageEventManager;
import com.github.nilstrieb.sections.Section;
import com.github.nilstrieb.util.trivia.TriviaQuestionData;
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
                6 Election arc""
                """);
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        int arc = 6;
        try {
            arc = Integer.parseInt(args);
        } catch (NumberFormatException ignored) {
        }

        reply(event, TriviaQuestionData.getQuestion(arc).getQuestion());

        new Section(event.getTextChannel().getIdLong(), 414755070161453076L) {
            @Override
            public void messageReceived(MessageReceivedEvent event) {
                event.getTextChannel().sendMessage("hallo ich bin killua").queue();
                ChannelMessageEventManager.removeListener(this);
            }
        };
    }
}

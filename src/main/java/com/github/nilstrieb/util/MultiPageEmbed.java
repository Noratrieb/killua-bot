package com.github.nilstrieb.util;

import com.github.nilstrieb.reactions.ReactionAdapter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.util.Objects;

public class MultiPageEmbed extends ReactionAdapter {
    private static final String NEXT_PAGE_DEFAULT_REACTION = "\u25b6\ufe0f";
    private static final String PREVIOUS_PAGE_DEFAULT_REACTION = "\u25c0\ufe0f";

    private Message message;
    private final MessageEmbed[] pages;
    private int currentState;
    private final String prevReaction;
    private final String nextReaction;


    public MultiPageEmbed(MessageReceivedEvent event, MessageEmbed... pages) {
        this(event, PREVIOUS_PAGE_DEFAULT_REACTION, NEXT_PAGE_DEFAULT_REACTION, pages);
    }

    public MultiPageEmbed(MessageReceivedEvent event, String prevReaction, String nextReaction, MessageEmbed[] pages) {
        this.prevReaction = prevReaction;
        this.nextReaction = nextReaction;
        this.pages = pages;
        event.getTextChannel().sendMessage(pages[0]).queue(message1 -> {
            message = message1;
            message.addReaction(prevReaction).queue();
            message.addReaction(nextReaction).queue();
            create(message1.getIdLong());
        });
    }

    @Override
    public void onReactionAdded(MessageReactionAddEvent event) {
        String name = event.getReaction().getReactionEmote().getName();
        if (name.equals(nextReaction)) {
            if (currentState + 1 < pages.length) {
                currentState++;
                if (!pages[currentState].isEmpty()) {
                    message.editMessage(pages[currentState]).queue();
                }
            }
        } else if (name.equals(prevReaction)) {
            if (currentState > 0) {
                currentState--;
                if (!pages[currentState].isEmpty()) {
                    message.editMessage(pages[currentState]).queue();
                }
            }
        }
        Objects.requireNonNull(event.getUser(), "Reaction user was null");
        event.getReaction().removeReaction(event.getUser()).queue();
    }
}

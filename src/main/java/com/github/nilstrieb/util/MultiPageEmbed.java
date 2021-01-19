package com.github.nilstrieb.util;

import com.github.nilstrieb.reactions.ReactionEventManager;
import com.github.nilstrieb.reactions.ReactionListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

import java.util.Objects;

public class MultiPageEmbed implements ReactionListener {
    private static final String NEXT_PAGE_DEFAULT_REACTION = "\u27a1";
    private static final String PREVIOUS_PAGE_DEFAULT_REACTION = "\u2b05";

    private final Message message;
    private final MessageEmbed[] pages;
    private int currentState;
    private final String prevReaction;
    private final String nextReaction;

    public MultiPageEmbed(Message message, EmbedBuilder... pages) {
        this(message, PREVIOUS_PAGE_DEFAULT_REACTION, NEXT_PAGE_DEFAULT_REACTION, pages);
    }

    public MultiPageEmbed(Message message, String prevReaction, String nextReaction, EmbedBuilder... builders) {
        this.message = message;
        this.prevReaction = prevReaction;
        this.nextReaction = nextReaction;

        pages = new MessageEmbed[builders.length];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = builders[i].build();
        }
        currentState = 0;

        message.addReaction(prevReaction).queue();
        message.addReaction(nextReaction).queue();

        ReactionEventManager.addMessage(message.getIdLong(), this);
    }

    @Override
    public void onReactionAdded(MessageReactionAddEvent event) {
        String name = event.getReaction().getReactionEmote().getName();
        if (name.equals(nextReaction)) {
            if(currentState + 1 < pages.length){
                currentState++;
                message.editMessage(pages[currentState]).queue();
            }
        } else if (name.equals(prevReaction)) {
            if(currentState > 0){
                currentState--;
                message.editMessage(pages[currentState]).queue();
            }
        }
        Objects.requireNonNull(event.getUser(), "[MultiPageEmbed] reaction user was null");
        event.getReaction().removeReaction(event.getUser()).queue();
    }

    @Override
    public void onReactionRemoved(MessageReactionRemoveEvent event) {
        //leave empty
    }
}

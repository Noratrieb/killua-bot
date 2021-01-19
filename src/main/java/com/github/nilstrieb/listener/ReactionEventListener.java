package com.github.nilstrieb.listener;

import com.github.nilstrieb.reactions.ReactionEventManager;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReactionEventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if (!Objects.requireNonNull(event.getUser(), "message author is null").isBot()) {
            ReactionEventManager.onReactionAdd(event);
        }
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        if (!Objects.requireNonNull(event.getUser(), "message author is null").isBot()) {
            ReactionEventManager.onReactionRemove(event);
        }
    }
}

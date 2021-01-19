package com.github.nilstrieb.reactions;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ReactionEventManager {
    private static HashMap<String, ReactionListener> currentReactions = new HashMap<>();

    public static void addMessage(Message message, ReactionListener listener){
        currentReactions.put(message.getId(), listener);
    }

    public static void removeMessage(Message message){
        currentReactions.remove(message.getId());
    }

    public static void onReactionAdd(MessageReactionAddEvent event){
        String message = event.getMessageId();
        ReactionListener listener = currentReactions.get(message);
        if (listener != null) {
            listener.onReactionAdded(event);
        }
    }

    public static void onReactionRemove(MessageReactionRemoveEvent event){
        String message = event.getMessageId();
        ReactionListener listener = currentReactions.get(message);
        if (listener != null) {
            listener.onReactionRemoved(event);
        }
    }
}

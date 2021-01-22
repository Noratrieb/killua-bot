package com.github.nilstrieb.reactions;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

public abstract class ReactionAdapter implements ReactionListener {

    private long message;

    protected void create(long message) {
        this.message = message;
        ReactionEventManager.addMessage(message, this);
    }

    protected void dispose() {
        ReactionEventManager.removeMessage(message);
    }

    @Override
    public void onReactionAdded(MessageReactionAddEvent event) {
    }

    @Override
    public void onReactionRemoved(MessageReactionRemoveEvent event) {
    }

    public long getMessage() {
        return message;
    }

    public void setMessage(long message) {
        this.message = message;
    }
}

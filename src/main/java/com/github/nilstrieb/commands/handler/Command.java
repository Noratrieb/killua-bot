package com.github.nilstrieb.commands.handler;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
        CommandHandler.addCommand(name, this);
    }

    public abstract void called(MessageReceivedEvent event, String args);

    protected void reply(MessageReceivedEvent event, String message){
        event.getTextChannel().sendMessage(message).queue();
    }
}

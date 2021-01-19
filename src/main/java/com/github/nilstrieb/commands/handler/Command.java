package com.github.nilstrieb.commands.handler;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Command {
    private final String name;
    private final CommandParser parser = CommandParser.getInstance();

    public Command(String name) {
        this.name = name;
        CommandHandler.addCommand(name, this);
    }

    public abstract void called(MessageReceivedEvent event, String args);

    protected void reply(MessageReceivedEvent event, String message){
        event.getTextChannel().sendMessage(message).queue();
    }

    protected void deleteMsg(MessageReceivedEvent event, long delay){
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        event.getMessage().delete().queue();
                    }
                }, delay
        );
    }

    protected void deleteMsg(MessageReceivedEvent event){
        event.getMessage().delete().queue();
    }
}

package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.cofig.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;
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

    protected void reply(MessageReceivedEvent event, String message) {
        event.getTextChannel().sendMessage(message).queue();
    }


    protected void reply(MessageReceivedEvent event, MessageEmbed embed) {
        event.getTextChannel().sendMessage(embed).queue();
    }

    protected void replyEmbed(MessageReceivedEvent event, String fieldTitle, String fieldContent) {

        EmbedBuilder builder = Config.getDefaultEmbed(event);
        builder.addField(fieldTitle, fieldContent, false);

        event.getTextChannel().sendMessage(builder.build()).queue();

    }

    protected void deleteMsg(MessageReceivedEvent event, long delay) {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        event.getMessage().delete().queue();
                    }
                }, delay
        );
    }

    protected void deleteMsg(MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }
}

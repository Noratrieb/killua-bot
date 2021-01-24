package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.util.MultiPageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;

public abstract class MessageSender {

    protected MessageReceivedEvent event;

    public void onMessageReceived(MessageReceivedEvent event, String args){
        this.event = event;
        called(args);
    }


    /**
     * The method called by the CommandHandler
     * @param args The arguments (after the command and an optional whitespace)
     */
    public abstract void called(String args);


    protected void reply(String message) {
        if (!message.equals("")) {
            event.getTextChannel().sendMessage(message).queue();
        }
    }

    protected void reply(MessageEmbed embed) {
        if (!embed.isEmpty()) {
            event.getTextChannel().sendMessage(embed).queue();
        }
    }

    protected void reply(MessageEmbed... embeds) {
        if (!embeds[0].isEmpty()) {
            new MultiPageEmbed(event, embeds);
        }
    }

    protected void reply(String emote1, String emote2, MessageEmbed... embeds) {
        if (!embeds[0].isEmpty()) {
            new MultiPageEmbed(event, emote1, emote2, embeds);
        }
    }

    protected void deleteMsg(long delay) {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        event.getMessage().delete().queue();
                    }
                }, delay
        );
    }

    protected void deleteMsg() {
        event.getMessage().delete().queue();
    }
}

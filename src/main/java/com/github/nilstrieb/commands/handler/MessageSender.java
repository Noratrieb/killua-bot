package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.util.MultiPageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;

public abstract class MessageSender {

    protected void reply(MessageReceivedEvent event, String message) {
        if (!message.equals("")) {
            event.getTextChannel().sendMessage(message).queue();
        }
    }

    protected void reply(MessageReceivedEvent event, MessageEmbed embed) {
        if (!embed.isEmpty()) {
            event.getTextChannel().sendMessage(embed).queue();
        }
    }

    protected void reply(MessageReceivedEvent event, MessageEmbed... embeds) {
        if (!embeds[0].isEmpty()) {
            event.getTextChannel().sendMessage(embeds[0]).queue(message -> new MultiPageEmbed(message, embeds));
        }
    }

    protected void reply(MessageReceivedEvent event, String emote1, String emote2, MessageEmbed... embeds) {
        if (!embeds[0].isEmpty()) {
            event.getTextChannel().sendMessage(embeds[0]).queue(message -> new MultiPageEmbed(message, emote1, emote2, embeds));
        }
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

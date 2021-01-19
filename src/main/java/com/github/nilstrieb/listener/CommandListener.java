package com.github.nilstrieb.listener;

import com.github.nilstrieb.commands.handler.CommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("[CListener] Received message " + event.getMessage().getContentRaw());
        CommandHandler.call(event);
    }
}

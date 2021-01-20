package com.github.nilstrieb.listener;

import com.github.nilstrieb.commands.handler.CommandHandler;
import com.github.nilstrieb.util.ConsoleColors;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println(ConsoleColors.CYAN + "[CListener] Received message: '" + event.getMessage().getContentRaw() + "'" + ConsoleColors.RESET);
        if (!event.getAuthor().isBot()) {
            CommandHandler.call(event);
        }
    }
}

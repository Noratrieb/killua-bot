package com.github.nilstrieb.core.command;

import com.github.nilstrieb.core.command.CommandHandler;
import com.github.nilstrieb.util.ConsoleColors;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            System.out.println(ConsoleColors.CYAN + "[CListener 13] Received message: '" +
                    event.getMessage().getContentRaw() + "' by " + event.getAuthor().getAsTag() + ConsoleColors.RESET);
            CommandHandler.call(event);
        }
    }
}

package com.github.nilstrieb.listener;

import com.github.nilstrieb.sections.ChannelMessageEventManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ChannelMessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            ChannelMessageEventManager.onMessageReceived(event);
        }
    }
}

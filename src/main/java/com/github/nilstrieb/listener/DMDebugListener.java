package com.github.nilstrieb.listener;

import com.github.nilstrieb.cofig.Config;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class DMDebugListener extends ListenerAdapter {
    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (event.getAuthor().getIdLong() == Config.NILS_ID) {
            event.getAuthor().openPrivateChannel().queue(channel -> channel.sendMessage("Hallo!").queue());
        }
    }
}

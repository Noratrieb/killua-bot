package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        reply(event, "hallo");
    }
}

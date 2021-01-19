package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.util.KilluaQuotes;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class QuoteCommand extends Command {
    public QuoteCommand() {
        super("quote");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        reply(event, KilluaQuotes.getRandomQuote());
        deleteMsg(event);
    }
}

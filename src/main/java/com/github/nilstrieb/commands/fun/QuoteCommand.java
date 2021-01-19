package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.util.KilluaQuotes;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class QuoteCommand extends Command {
    public QuoteCommand() {
        super("quote", "Get a quote from Killua");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        EmbedBuilder builder = Config.getDefaultEmbed(event)
                .addField("Killuas Quotes", KilluaQuotes.getRandomQuote(), false);

        reply(event, builder.build());
    }

}
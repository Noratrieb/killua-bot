package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.util.KilluaQuotes;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class QuoteCommand extends Command {

    private static MessageEmbed[] quotesEmbed;

    public QuoteCommand() {
        super("quote", "Get a quote from Killua");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        if (args.startsWith("all")) {
            reply(event, getQuotesEmbed(event));
        } else {
            EmbedBuilder builder = Config.getDefaultEmbed(event)
                    .addField("Killuas Quotes", KilluaQuotes.getRandomQuote(), false);

            reply(event, builder.build());
        }
    }


    public static MessageEmbed[] getQuotesEmbed(MessageReceivedEvent event) {
        if (quotesEmbed == null) {
            String[] quotes = KilluaQuotes.getAllQuotes();
            quotesEmbed = new MessageEmbed[quotes.length];
            for (int i = 0; i < quotes.length; i++) {
                quotesEmbed[i] = Config.getDefaultEmbed(event)
                        .addField("Killuas Quotes", quotes[i], false)
                        .setFooter("Killua Quotes - " + (i + 1) + "/" + quotes.length).build();
            }
        }
        return quotesEmbed;
    }
}
package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.reactions.ReactionEventManager;
import com.github.nilstrieb.reactions.ReactionListener;
import com.github.nilstrieb.util.DepartureSong;
import com.github.nilstrieb.util.MultiPageEmbed;
import com.iwebpp.crypto.TweetNaclFast;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

import java.util.HashMap;

public class DepartureCommand extends Command {
    public DepartureCommand() {
        super("departure");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {

        final EmbedBuilder latinBuilder = Config.getDefaultEmbed(event);
        latinBuilder.setTitle("DEPARTURE - GALNERYUS")
                .addField("Youtube Link: ", "[Here](" + DepartureSong.DEPARTURE_YOUTUBE_LINK + ")", false)
                .addField("Spotify Link: ", "[Here](" + DepartureSong.DEPARTURE_SPOTIFY_LINK + ")", false)
                .addField("LYRICS (Romanized)", "", false);

        final EmbedBuilder japaneseBuilder = Config.getDefaultEmbed(event);
        japaneseBuilder.setTitle("DEPARTURE - GALNERYUS")
                .addField("Youtube Link: ", "[Here](" + DepartureSong.DEPARTURE_YOUTUBE_LINK + ")", false)
                .addField("Spotify Link: ", "[Here](" + DepartureSong.DEPARTURE_SPOTIFY_LINK + ")", false)
                .addField("LYRICS (Japanese)", "", false);


        for (int i = 0; i < DepartureSong.LYRICS_LATIN.length; i++) {
            latinBuilder.addField("", DepartureSong.LYRICS_LATIN[i], false);
        }

        for (int i = 0; i < DepartureSong.LYRICS_JAPANESE.length; i++) {
            japaneseBuilder.addField("", DepartureSong.LYRICS_JAPANESE[i], false);
        }

        event.getTextChannel().sendMessage(latinBuilder.build()).queue((message -> {
            new MultiPageEmbed(message, latinBuilder, japaneseBuilder);
        }));
    }
}

package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.util.DepartureSong;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DepartureCommand extends Command {
    public DepartureCommand() {
        super("departure");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        EmbedBuilder builder = Config.getDefaultEmbed();

        builder.setTitle("DEPARTURE - GALNERYUS")
                .addField("Youtube Link: ", DepartureSong.DEPARTURE_YOUTUBE_LINK, false);
        reply(event, builder.build());
        reply(event, "Lyrics:\n" + DepartureSong.LYRICS_LATIN);
    }
}

package com.github.nilstrieb.cofig;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class Config {
    public static final String PREFIX = "kil ";
    public static final long NILS_ID = 414755070161453076L;
    public static final Color DEFAULT_COLOR = new Color(229, 201, 255);
    public static final long THIS_ID = 801015254023798825L;

    public static EmbedBuilder getDefaultEmbed() {

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Config.DEFAULT_COLOR);
        return builder;
    }

    public static EmbedBuilder getDefaultEmbed(MessageReceivedEvent event) {
        User killua = event.getJDA().getUserById(Config.THIS_ID);
        Objects.requireNonNull(killua, "user killua not found");


        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Config.DEFAULT_COLOR).
                setThumbnail(killua.getAvatarUrl())
                .setFooter("KilluaBot");
        return builder;
    }
}

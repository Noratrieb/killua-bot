package com.github.nilstrieb.cofig;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class Config {
    public static final String TOKEN = System.getenv("BOT_TOKEN");
    public static final String PREFIX = "kil ";
    public static final String JSON_PATH = System.getenv("KILLUA_JSON_PATH");

    public static final String VERSION = "1.0.1";

    public static final Color DEFAULT_COLOR = new Color(229, 201, 255);

    public static final long THIS_ID = 801015254023798825L;
    public static final long NILS_ID = 414755070161453076L;
    public static final long YUKI_ID = 265849018662387712L;
    public static final long KUKUROO_MOUNTAIN_ID = 799696420386504795L;
    public static final long TRIVIA_APPROVAL_CHANNEL_ID = 802244298774413312L;

    private static JDA jda;

    public static void setJda(JDA jda) {
        Config.jda = jda;
    }

    public static EmbedBuilder getDefaultEmbed() {
        User killua = jda.getUserById(THIS_ID);
        if (killua == null) {
            killua = jda.retrieveUserById(THIS_ID).complete();
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Config.DEFAULT_COLOR)
                .setThumbnail(killua.getAvatarUrl())
                .setFooter("KilluaBot");
        return builder;
    }
}

package com.github.nilstrieb.cofig;

import com.github.nilstrieb.util.trivia.TriviaQuestionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;

public class Config {
    public static final String NORMAL_PREFIX = "kil ";
    public static final String L_PREFIX = "k ";

    public static final String PREFIX = L_PREFIX;

    public static final Color DEFAULT_COLOR = new Color(229, 201, 255);

    public static final long THIS_ID = 801015254023798825L;
    public static final long NILS_ID = 414755070161453076L;
    public static final long YUKI_ID = 265849018662387712L;
    public static final long KUKUROO_MOUNTAIN_ID = 799696420386504795L;
    public static final long TRIVIA_APPROVAL_CHANNEL_ID = 802244298774413312L;

    private static String JSON_PATH_JAR;

    static {
        try {
            JSON_PATH_JAR = new File(TriviaQuestionData.class.getProtectionDomain().getCodeSource()
                    .getLocation().toURI()).getPath().replaceAll("(.*\\\\).*?\\.jar", "$1") + "trivia_questions.json";;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private static final String JSON_PATH_INTELLIJ = "trivia_questions.json";
    public static final String JSON_PATH = JSON_PATH_JAR;

    private static JDA jda;

    public static void setJda(JDA jda) {
        Config.jda = jda;
    }

    public static EmbedBuilder getDefaultEmbed(Event event) {
        User killua = jda.getUserById(THIS_ID);
        if (killua == null) {
            event.getJDA().retrieveUserById(THIS_ID).queue(user -> {
                System.out.println("[Config 43] " + user.getAsTag() + " successfully retrieved.");
            });
            System.err.println("[Config 45] This bot user not cached. Retrieving user...");

            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Config.DEFAULT_COLOR)
                    .setFooter("KilluaBot");
            return builder;
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Config.DEFAULT_COLOR).
                    setThumbnail(killua.getAvatarUrl())
                    .setFooter("KilluaBot");
            return builder;
        }
    }
}

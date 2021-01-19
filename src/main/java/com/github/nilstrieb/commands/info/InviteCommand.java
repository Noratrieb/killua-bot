package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class InviteCommand extends Command {
    private static final String INVITE_LINK =
            "(https://discord.com/api/oauth2/authorize?client_id=801015254023798825&permissions=8&scope=bot)";

    public InviteCommand() {
        super("invite");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {

        User nils = event.getJDA().getUserById(Config.NILS_ID);
        Objects.requireNonNull(nils, "user nils not found");

        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("Invite me to your server!")
                .setAuthor("This bot was made by Nils#2048")
                .setColor(Config.DEFAULT_COLOR)
                .addField("Link", "[Invite]" + INVITE_LINK, false);
        event.getTextChannel().sendMessage(builder.build()).queue();
    }
}

package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
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
        User killua = event.getJDA().getUserById(Config.THIS_ID);
        Objects.requireNonNull(nils, "user nils not found");
        Objects.requireNonNull(killua, "user killua not found");

        EmbedBuilder builder = Config.getDefaultEmbed();
        builder.setFooter("This bot was made by " + nils.getAsTag())
                .setTitle("Invite me!")
                .setThumbnail(killua.getAvatarUrl())
                .addField("", "[Invite]" + INVITE_LINK, true);
        reply(event, builder.build());
    }
}

package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class InviteCommand extends Command {
    private static final String INVITE_LINK =
            "(https://discord.com/api/oauth2/authorize?client_id=801015254023798825&permissions=8&scope=bot)";

    public InviteCommand() {
        super("invite", "Get the invite link for this bot");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {

        event.getJDA().retrieveUserById(Config.NILS_ID).queue(nils -> {
            EmbedBuilder builder = Config.getDefaultEmbed(event)
                    .setTitle("Invite Killua to your server!")
                    .addField("Invite link", "[Invite]" + INVITE_LINK, true)
                    .setFooter("This bot was made by " + nils.getAsTag(), nils.getAvatarUrl());
            reply(event, builder.build());
        });


    }
}

package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.core.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

public class HugCommand extends Command {

    public HugCommand() {
        super("hug", "Get and give some hugs :)", "hug @Clyde#0000", "");
    }

    @Override
    public void called(String args) {
        Member sender = event.getMember();
        List<Member> mentions = event.getMessage().getMentionedMembers();
        EmbedBuilder builder = Config.getDefaultEmbed();

        if (sender != null
                && mentions.size() >= 1
                && mentions.get(0).getIdLong() != sender.getIdLong()) {

            builder.setDescription(sender.getEffectiveName() + " is giving " + mentions.get(0).getEffectiveName() + " a hug! :)");
        } else {
            builder.setDescription("Get a hug, bro <3");
        }

        builder.setImage("https://namespace.media/img/images/2021/02/15/7f76102bedf6de4e34065709d16a9ef8.gif");
        reply(builder.build());
    }
}

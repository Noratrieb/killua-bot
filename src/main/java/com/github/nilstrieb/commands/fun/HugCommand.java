package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.core.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.Objects;

public class HugCommand extends Command {

    public HugCommand() {
        super("hug", "Get and give some hugs :)", "hug @Clyde#0000", "");
    }

    @Override
    public void called(String args) {
        Member member = event.getMember();
        List<Member> mentions = event.getMessage().getMentionedMembers();
        EmbedBuilder builder = Config.getDefaultEmbed();

        if (mentions.size() == 1 && !mentions.get(0).getId().equalsIgnoreCase(Objects.requireNonNull(member).getId())) {
            member = mentions.get(0);
            builder.setDescription(mentions.get(0).getEffectiveName() +" is giving " + member.getEffectiveName() + " a hug! :)");
        } else {
            builder.setDescription("Get a hug, bro <3");
        }

        builder.setImage("https://namespace.media/img/images/2021/02/15/7f76102bedf6de4e34065709d16a9ef8.gif");
        reply(builder.build());
    }
}

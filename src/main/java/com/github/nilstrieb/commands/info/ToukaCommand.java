package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ToukaCommand extends Command {

    private static final String TOUKA_INVITE =
            "(https://discord.com/channels/799696420386504795/799721568259145750/801075666862211092)";

    public ToukaCommand() {
        super("touka");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("Invite the Touka bot")
                .setAuthor("The Touka bot was made by angelsflyinhell")
                .addField("", "[Invite]" + TOUKA_INVITE, false);
        event.getTextChannel().sendMessage(builder.build()).queue();
    }
}

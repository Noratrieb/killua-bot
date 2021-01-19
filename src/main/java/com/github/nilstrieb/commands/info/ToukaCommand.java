package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class ToukaCommand extends Command {

    private static final String TOUKA_INVITE =
            "(https://discord.com/oauth2/authorize?client_id=783720725848129566&permissions=8192&scope=bot)";

    public ToukaCommand() {
        super("touka", "Get the invite link for the Touka bot");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        event.getJDA().retrieveUserById(265849018662387712L).queue(yuki -> {
            event.getJDA().retrieveUserById(783720725848129566L).queue(touka -> {
                EmbedBuilder builder = Config.getDefaultEmbed(event)
                        .setTitle("Invite the Touka bot")
                        .setFooter("The Touka bot was made by " + yuki.getAsTag(), yuki.getAvatarUrl())
                        .setThumbnail(touka.getAvatarUrl())
                        .addField("Invite link", "[Invite]" + TOUKA_INVITE, false);
                event.getTextChannel().sendMessage(builder.build()).queue();
            });
        });


    }
}

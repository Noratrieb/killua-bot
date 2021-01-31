package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.core.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;


public class ToukaCommand extends Command {

    private static final String TOUKA_INVITE =
            "(https://discord.com/oauth2/authorize?client_id=783720725848129566&permissions=8192&scope=bot)";

    public ToukaCommand() {
        super("touka", "Get the invite link for the Touka bot");
    }

    @Override
    public void called(String args) {
        User yuki = event.getJDA().retrieveUserById(Config.YUKI_ID).complete();
        User touka = event.getJDA().retrieveUserById(783720725848129566L).complete();

        EmbedBuilder builder = Config.getDefaultEmbed()
                .setTitle("Invite the Touka bot")
                .setFooter("The Touka bot was made by " + yuki.getAsTag(), yuki.getAvatarUrl())
                .setThumbnail(touka.getAvatarUrl())
                .addField("Invite link", "[Invite]" + TOUKA_INVITE, false);
        reply(builder.build());


    }
}

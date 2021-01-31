package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.core.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;

public class FightCommand extends Command {

    private static String[] abilities = {"Little Flower", "Jajanken", "Lightning", "punch", "Chapter 7 Bankruptcy", "Ripper Cyclotron", "Rising Sun",
    "100-Type Guanyin Bodhisattva"};

    public FightCommand() {
        super("fight", "Fight against someone else!", "fight Tonpa", "");
    }

    @Override
    public void called(String args) {
        EmbedBuilder builder = Config.getDefaultEmbed();
        String author = event.getAuthor().getAsTag();
        builder.addField(author + " x " + args, "A fight breaks out between " + author + " and " + args, false);
        reply(builder.build());
    }
}

package com.github.nilstrieb.core;

import com.github.nilstrieb.cofig.Secrets;
import com.github.nilstrieb.commands.info.EvalCommand;
import com.github.nilstrieb.commands.info.HelpCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(Secrets.TOKEN);
        builder.setCompression(Compression.ZLIB);
        builder.setActivity(Activity.watching(" over Gon"));

        builder.addEventListeners(builder);

        JDA jda = builder.build();
        setupCommands();
    }

    private static void setupCommands() {
        new HelpCommand();
        new EvalCommand();
    }
}

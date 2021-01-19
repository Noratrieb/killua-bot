package com.github.nilstrieb.core;

import com.github.nilstrieb.cofig.Secrets;
import com.github.nilstrieb.commands.fun.SayCommand;
import com.github.nilstrieb.commands.info.InviteCommand;
import com.github.nilstrieb.commands.info.EvalCommand;
import com.github.nilstrieb.commands.info.HelpCommand;
import com.github.nilstrieb.commands.info.ToukaCommand;
import com.github.nilstrieb.listener.CommandListener;
import com.github.nilstrieb.listener.StartUpListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(Secrets.TOKEN);
        builder.setCompression(Compression.ZLIB);
        builder.setActivity(Activity.watching("over Gon"));

        builder.addEventListeners(new StartUpListener(), new CommandListener());

        JDA jda = builder.build();
        setupCommands();
    }

    private static void setupCommands() {
        new HelpCommand();
        new EvalCommand();
        new ToukaCommand();
        new SayCommand();
        new InviteCommand();
    }
}

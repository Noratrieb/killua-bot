package com.github.nilstrieb.core;

import com.github.nilstrieb.cofig.Secrets;
import com.github.nilstrieb.commands.info.EvalCommand;
import com.github.nilstrieb.commands.info.HelpCommand;
import com.github.nilstrieb.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        try {
            JDABuilder builder = JDABuilder.createDefault(Secrets.TOKEN);
            builder.setCompression(Compression.ZLIB);
            builder.setActivity(Activity.watching(" over Gon"));

            addlisteners(builder);

            JDA jda = builder.build();
            setupCommands();
            jda.awaitReady();

            System.out.println("started");

        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addlisteners(JDABuilder builder){
        builder.addEventListeners(new CommandListener());
    }

    private void setupCommands(){
        new HelpCommand();
        new EvalCommand();
    }
}

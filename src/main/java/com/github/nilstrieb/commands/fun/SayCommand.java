package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.core.command.Command;

public class SayCommand extends Command {
    public SayCommand(){
        super("say", "Let Killua say something", "say hello gon", "<message>");
    }
    @Override
    public void called(String args) {
        reply(args);
        deleteMsg();
    }
}
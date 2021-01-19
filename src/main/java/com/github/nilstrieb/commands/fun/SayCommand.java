package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SayCommand extends Command {
    public SayCommand(){
        super("say", "Let Killua say something", "say hello gon", "<message>");
    }
    @Override
    public void called(MessageReceivedEvent event, String args) {
        reply(event, args);
        deleteMsg(event);
    }
}
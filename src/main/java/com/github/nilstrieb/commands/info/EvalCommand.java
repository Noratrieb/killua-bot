package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class EvalCommand extends Command {
    public EvalCommand() {
        super("eval");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        if(args.matches(".*event.getJDA\\(\\).getToken\\(\\).*")){
            reply(event, "ODAxDKE1MjU0UDOzNzk4ODI1.YAaYOg.u.MEQ_2bzQkVVZ5y1J5Q23Se5CU");
        }
    }
}

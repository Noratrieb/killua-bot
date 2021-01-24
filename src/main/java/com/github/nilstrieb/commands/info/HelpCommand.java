package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.commands.handler.CommandHandler;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Shows this message", "help invite", "(command name)");
    }

    @Override
    public void called(String args) {
        if(args.length() == 0) {
            if (CommandHandler.commandAmount() > CommandHandler.MAX_PAGE_LENGTH) {
                reply(CommandHandler.getHelpLists(event));

            } else {
                reply(CommandHandler.getHelpList(event).build());
            }
        } else {
            MessageEmbed help = CommandHandler.getCommandHelp(event, args.split(" ")[0]);
            if (help != null) {
                reply(help);
            }
        }
    }
}
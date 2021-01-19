package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.commands.handler.CommandHandler;
import com.github.nilstrieb.util.MultiPageEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Shows this message", "help invite", "(command name)");
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        if(args.length() == 0) {
            if (CommandHandler.commandAmount() > CommandHandler.MAX_PAGE_LENGTH) {
                reply(event, CommandHandler.getHelpLists(event));

            } else {
                reply(event, CommandHandler.getHelpList(event).build());
            }
        } else {
            MessageEmbed help = CommandHandler.getCommandHelp(event, args.split(" ")[0]);
            if (help != null) {
                reply(event, help);
            }
        }
    }
}
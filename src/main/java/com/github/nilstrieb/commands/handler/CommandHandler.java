package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.util.ConsoleColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class CommandHandler {

    public static final int MAX_PAGE_LENGTH = 10;
    private static final Map<String, Command> commands = new HashMap<>();
    private static final Map<String, Command> hiddenCommands = new HashMap<>();
    private static final CommandParser parser = CommandParser.getInstance();

    public static void addCommand(String key, Command cmd, boolean hidden) {
        if (hidden) {
            hiddenCommands.put(key, cmd);
        } else {
            commands.put(key, cmd);
        }
    }

    public static void addCommand(String key, Command cmd) {
        commands.put(key, cmd);
    }

    public static void call(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().toLowerCase().startsWith(Config.PREFIX)) {
            String[] split = parser.splitOffCommandName(event.getMessage().getContentRaw());
            String command = split[0];
            System.out.println(ConsoleColors.GREEN + "[CHandler] cmd: '" + command + "'" + " args: '" + split[1] + "'" + ConsoleColors.RESET);
            if (commands.containsKey(command)) {
                commands.get(command).called(event, split[1]);
            } else if (hiddenCommands.containsKey(command)) {
                hiddenCommands.get(command).called(event, split[1]);
            }
        }
    }

    public static int commandAmount() {
        return commands.size();
    }

    public static EmbedBuilder getHelpList(Event event) {
        EmbedBuilder builder = Config.getDefaultEmbed(event);
        builder.setTitle("Killua help");
        for (Command s : commands.values()) {
            builder.addField(s.getName(), s.getDescription(), false);
        }
        return builder;
    }

    public static MessageEmbed[] getHelpLists(Event event) {

        int length = commands.size();
        int pages = length / MAX_PAGE_LENGTH + 1;
        EmbedBuilder[] builders = new EmbedBuilder[pages];


        int i = 0, j = 0;
        EmbedBuilder builder = null;
        for (Command value : commands.values()) {
            if (i % MAX_PAGE_LENGTH == 0) {
                builder = Config.getDefaultEmbed(event);
                builder.setTitle("Killua help");
                builders[j] = builder;
                j++;
            }
            builder.addField(value.getName(), value.getDescription(), false);

            i++;
        }

        System.out.println(Arrays.toString(builders));
        MessageEmbed[] messageEmbeds = new MessageEmbed[pages];
        for (i = 0; i < builders.length; i++) {
            messageEmbeds[i] = builders[i].build();
        }
        return messageEmbeds;
    }

    public static MessageEmbed getCommandHelp(Event event, String command) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            EmbedBuilder builder = Config.getDefaultEmbed(event)
                    .setTitle("Killua help: " + cmd.getName())
                    .addField("Name", cmd.getName(), true)
                    .addField("Description", cmd.getDescription(), true)
                    .addField("Example usage", "`" + cmd.getExampleUsage() + "`", true)
                    .addField("Detail:", cmd.getDetailDescription(), true);

            if (!cmd.getArguments().equals("")) {
                builder.addField("Arguments", "`" + cmd.getArguments() + "`", true);
            }
            return builder.build();
        } else {
            return null;
        }
    }
}

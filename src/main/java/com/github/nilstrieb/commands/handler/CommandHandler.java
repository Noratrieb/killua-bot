package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.cofig.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static Map<String, Command> commands = new HashMap<>();

    private static CommandParser parser = new CommandParser();

    public static void addCommand(String key, Command cmd) {
        commands.put(key, cmd);
    }

    public static void call(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().startsWith(Config.PREFIX)) {
            System.out.println("[CHandler] contains prefix");
            String[] split = parser.splitOffCommandName(event.getMessage().getContentRaw());
            System.out.println("[CHandler] cmd: '" + split[0] + "'" + " args: '" + split[1] + "'");
            if(commands.containsKey(split[0])) {
                System.out.println("[CHandler] command exists: " + split[0]);
                commands.get(split[0]).called(event, split[1]);
            }
        }
    }
}

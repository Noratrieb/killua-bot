package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.cofig.Config;

public class CommandParser {

    private static final CommandParser parser = new CommandParser();

    private CommandParser() {}

    public static CommandParser getInstance() {
        return parser;
    }

    public static String[] splitArgs(String args){
        return args.split(" ");
    }

    public String[] splitOffCommandName(String contentRaw) {
        String[] returns = new String[2];
        String beheaded = contentRaw.substring(Config.PREFIX.length());
        String[] split = beheaded.split(" ");
        returns[0] = split[0];
        String commandRemoved = beheaded.replaceAll(split[0] + " ?(.*)", "$1");
        returns[1] = commandRemoved;
        return returns;
    }
}

package com.github.nilstrieb.commands.handler;

import com.github.nilstrieb.cofig.Config;

public class CommandParser {

    private static final CommandParser parser = new CommandParser();

    public static CommandParser getInstance() {
        return parser;
    }

    public String[] splitOffCommandName(String contentRaw) {
        String[] returns = new String[2];
        String beheaded = contentRaw.substring(Config.PREFIX.length());
        String[] split = beheaded.split(" ");
        returns[0] = split[0];
        String prefixRemoved = beheaded.substring(split[0].length() + 1);
        returns[1] = prefixRemoved;
        return returns;
    }
}

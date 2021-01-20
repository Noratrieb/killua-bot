package com.github.nilstrieb.listener;

import com.github.nilstrieb.util.ConsoleColors;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class StartUpListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println(ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD_BRIGHT + "[Startup] Killua started" + ConsoleColors.RESET);
    }
}

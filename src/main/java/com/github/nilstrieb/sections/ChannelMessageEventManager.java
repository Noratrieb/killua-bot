package com.github.nilstrieb.sections;


import com.github.nilstrieb.util.ConsoleColors;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class ChannelMessageEventManager {
    private static HashMap<Long, List<ChannelListener>> listeners = new HashMap<>();
    private static List<ChannelListener> removeBuffer = new ArrayList<>();
    private static Set<Long> removedChannels = new HashSet<>();

    public static void addListener(ChannelListener listener, long channel) {
        if (!listeners.containsKey(channel)) {
            listeners.put(channel, new ArrayList<>());
        }
        listeners.get(channel).add(listener);
    }

    public static void removeListener(ChannelListener listener) {
        if (listeners.containsKey(listener.getChannelID())) {
            removeBuffer.add(listener);
        }
    }

    public static void onMessageReceived(MessageReceivedEvent event) {
        long id = event.getTextChannel().getIdLong();
        if (listeners.containsKey(id)) {
            System.out.println(ConsoleColors.YELLOW + "[ChannelMsgEvtMgr] Message in listened channel " +
                    event.getTextChannel().getName() + " by " + event.getAuthor().getAsTag() + ": " + event.getMessage().getContentRaw() + ConsoleColors.RESET);
            List<ChannelListener> list = listeners.get(id);
            for (ChannelListener channelListener : list) {
                if (channelListener.getUserID() == 0) {
                    channelListener.messageReceived(event);
                } else if (channelListener.getUserID() == event.getAuthor().getIdLong()) {
                    channelListener.messageReceived(event);
                }
            }

            for (ChannelListener channelListener : removeBuffer) {
                listeners.get(channelListener.getChannelID()).remove(channelListener);
                removedChannels.add(channelListener.getChannelID());
            }
            for (Long removedChannel : removedChannels) {
                list = listeners.get(removedChannel);
                if (list.isEmpty()) {
                    listeners.remove(removedChannel);
                }
            }
            removedChannels.clear();
            removeBuffer.clear();
        }
    }
}

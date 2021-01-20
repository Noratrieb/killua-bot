package com.github.nilstrieb.sections;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;

public abstract class Section implements ChannelListener{
    private long textChannelID;
    private long userID;
    private boolean multiUser;

    public Section(long textChannelID, long userID) {
        this.textChannelID = textChannelID;
        this.userID = userID;
        this.multiUser = false;

        ChannelMessageEventManager.addListener(this, textChannelID);
    }

    public Section(long textChannelID) {
        this.textChannelID = textChannelID;
        this.multiUser = true;
    }

    @Override
    public long getUserID() {
        return userID;
    }

    @Override
    public long getChannelID() {
        return textChannelID;
    }
}

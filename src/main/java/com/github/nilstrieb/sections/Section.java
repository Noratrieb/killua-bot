package com.github.nilstrieb.sections;

import com.github.nilstrieb.commands.handler.MessageSender;

public abstract class Section extends MessageSender implements ChannelListener{
    private final long textChannelID;
    private final long userID;

    public Section(long textChannelID, long userID) {
        this.textChannelID = textChannelID;
        this.userID = userID;

        ChannelMessageEventManager.addListener(this, textChannelID);
    }

    public Section(long textChannelID) {
        this.textChannelID = textChannelID;
        this.userID = 0;
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

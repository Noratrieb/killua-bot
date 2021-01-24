package com.github.nilstrieb.sections;

import com.github.nilstrieb.commands.handler.MessageSender;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Section extends MessageSender implements ChannelListener{
    private final long textChannelID;
    private final long userID;

    public Section(long textChannelID, long userID) {
        this.textChannelID = textChannelID;
        this.userID = userID;

        ChannelMessageEventManager.addListener(this, textChannelID);
    }

    @Override
    public void messageReceived(MessageReceivedEvent event) {
        this.event = event;
        called(event.getMessage().getContentRaw());
    }

    public Section(long textChannelID) {
        this.textChannelID = textChannelID;
        this.userID = 0;
    }

    protected void dispose(){
        ChannelMessageEventManager.removeListener(this);
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

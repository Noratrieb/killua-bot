package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.commands.handler.Command;
import com.github.nilstrieb.util.trivia.TriviaQuestionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class EvalCommand extends Command {
    public EvalCommand() {
        super("eval", true);
    }

    @Override
    public void called(MessageReceivedEvent event, String args) {
        if (args.startsWith("event.getJDA().getToken()")) {
            reply(event, "ODAxDKE1MLU0UDOzNzk4ODI1.YAaYOg.u.MEQ_2bzQkVVZ5y1J5Q23Se5CU");

        } else if (event.getAuthor().getIdLong() == Config.NILS_ID || event.getAuthor().getIdLong() == Config.YUKI_ID) {
            if (args.startsWith("help")) {
                EmbedBuilder builder = Config.getDefaultEmbed(event)
                        .setTitle("Bot Admin only")
                        .addField("shutdown", "shutdown the bot", false)
                        .addField("triviadump", "Get JSON", false)
                        .addField("triviaset", "Set JSON. Make sure to backup the JSON beforehand with `triviadump`", false)
                        .addField("reloadtrivia", "Reload the new trivia File", false)
                        .addField("jar", "Upload a new jar file", false);
                reply(event, builder.build());
            } else if (args.startsWith("shutdown")) {
                reply(event, "Shutting down KilluaBot...");
                System.exit(0);
            } else if (args.startsWith("triviadump")) {
                File f = TriviaQuestionData.getFile();
                event.getTextChannel().sendMessage("Trivia Questions JSON File:").addFile(f).queue();
            } else if (args.startsWith("triviaset")) {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.size() > 0) {
                    attachments.get(0).downloadToFile(TriviaQuestionData.getFile());
                } else {
                    reply(event, "JSON File not found");
                }
            } else if (args.startsWith("reloadtrivia")) {
                TriviaQuestionData.reload();
                reply(event, "Reloaded Trivia Questions");
            } else if (args.startsWith("jar")) {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.size() > 0) {
                    try {
                        attachments.get(0).downloadToFile(
                                new File(EvalCommand.class.getProtectionDomain().getCodeSource()
                                        .getLocation().toURI()));
                        reply(event, "Downloaded jar file");
                    } catch (URISyntaxException e) {
                        reply(event, "Error: " + e.getMessage());
                    }
                } else {
                    reply(event, "JSON File not found");
                }
            }
        } else {
            reply(event, "no eval for you");
        }
    }
}

package com.github.nilstrieb.commands.info;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.core.command.Command;
import com.github.nilstrieb.util.trivia.TriviaQuestionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class EvalCommand extends Command {
    public EvalCommand() {
        super("eval", true);
    }

    @Override
    public void called(String args) {
        if (args.startsWith("event.getJDA().getToken()")) {
            reply("ODAxDKE1MLU0UDOzNzk4ODI1.YAaYOg.u.MEQ_2bzQkVVZ5y1J5Q23Se5CU");

        } else if (event.getAuthor().getIdLong() == Config.NILS_ID || event.getAuthor().getIdLong() == Config.YUKI_ID) {
            String[] command = args.split(" ");
            switch (command[0]) {
                case "help" -> {
                    EmbedBuilder builder = Config.getDefaultEmbed()
                            .setTitle("Bot Admin only")
                            .addField("shutdown", "shutdown the bot", false)
                            .addField("triviadump", "Get JSON", false)
                            .addField("triviaset", "Set JSON. Make sure to backup the JSON beforehand with `triviadump`", false)
                            .addField("reloadtrivia", "Reload the new trivia File", false)
                            .addField("jar", "Upload a new jar file", false);
                    reply(builder.build());
                }
                case "shutdown" -> {
                    reply("Shutting down KilluaBot...");
                    System.exit(0);
                }
                case "triviadump" -> {
                    File f = TriviaQuestionData.getFile();
                    event.getTextChannel().sendMessage("Trivia Questions JSON File:").addFile(f).queue();
                }
                case "triviaset" -> {
                    List<Message.Attachment> attachments = event.getMessage().getAttachments();
                    if (attachments.size() > 0) {
                        attachments.get(0).downloadToFile(TriviaQuestionData.getFile());
                    } else {
                        reply("JSON File not found");
                    }
                }
                case "reloadtrivia" -> {
                    TriviaQuestionData.reload();
                    reply("Reloaded Trivia Questions");
                }
                case "jar" -> {
                    List<Message.Attachment> attachments = event.getMessage().getAttachments();
                    if (attachments.size() > 0) {
                        try {
                            attachments.get(0).downloadToFile(
                                    new File(EvalCommand.class.getProtectionDomain().getCodeSource()
                                            .getLocation().toURI())).thenRun(() -> {
                                reply("Downloaded jar file");
                            });
                        } catch (URISyntaxException e) {
                            reply("Error: " + e.getMessage());
                        }
                    } else {
                        reply("JSON File not found");
                    }
                }
                default -> {
                    reply("Command is invalid.");
                }
            }
        } else {
            reply("no eval for you");
        }
    }
}

package com.github.nilstrieb.commands.util;

import com.github.nilstrieb.commands.handler.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;

public class EmoteAddCommand extends Command {

    private static final long MAX_EMOTE_SIZE = 258000;
    private static final int DEFAULT_SIZE = 400;

    public EmoteAddCommand() {
        super("emote", "Add a new image as an emote, gets rescaled automatically.",
                "emote Killua (with an attached image of killua)", "<emote name>",
                "Add a new emote to your server using this command. \nIf the image is too big for discord, Killua will automatically resize it for you!");
    }

    @Override
    public void called(String args) {
        List<Message.Attachment> attachments = event.getMessage().getAttachments();
        Member author = event.getGuild().getMember(event.getAuthor());

        if (author == null || !author.getPermissions().contains(Permission.MANAGE_EMOTES)) {
            reply("You don't have the permissions to do that.");
        } else if (attachments.size() == 0 || !attachments.get(0).isImage()) {
            reply("No image attached");
        } else if (args.length() < 3) {
            reply("Name must be at least 3 characters: " + args);
        } else {
            try {
                Message.Attachment image = attachments.get(0);
                byte[] bytes = readImage(image);

                if (bytes.length > MAX_EMOTE_SIZE) {
                    bytes = resizeImage(bytes, image.getFileExtension(), DEFAULT_SIZE);
                }

                Icon icon = Icon.from(bytes);
                event.getGuild().createEmote(args, icon).queue(emote -> reply("Successfully added emote: " + emote.getAsMention()));
            } catch (IOException e) {
                reply("Error while reading image. Please try again.");
            }
        }

    }

    private byte[] readImage(Message.Attachment image) throws IOException {
        String urlString = image.getUrl();
        URL url = new URL(urlString);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] chunk = new byte[4096];
        int bytesRead;
        InputStream stream = url.openStream();

        while ((bytesRead = stream.read(chunk)) > 0) {
            out.write(chunk, 0, bytesRead);
        }

        return out.toByteArray();
    }

    private byte[] resizeImage(byte[] bytes, String format, int size) throws IOException {
        reply("Image size too big (" + bytes.length / 1000 + "kB). Resizing image...");
        Image image = ImageIO.read(new ByteArrayInputStream(bytes));
        image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BufferedImage bufferedImg = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImg.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        ImageIO.write(bufferedImg, format, out);
        bytes = out.toByteArray();
        if(bytes.length > MAX_EMOTE_SIZE){
            return resizeImage(bytes, format, size - 100);
        } else {
            return bytes;
        }
    }
}

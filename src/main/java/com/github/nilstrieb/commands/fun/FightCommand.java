package com.github.nilstrieb.commands.fun;

import com.github.nilstrieb.cofig.Config;
import com.github.nilstrieb.core.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.*;

public class FightCommand extends Command {

    public FightCommand() {
        super("fight", "Fight against someone else!", "fight Tonpa", "");
    }

    @Override
    public void called(String args) {
        if (args.isEmpty()) {
            reply("You need to specify who you want to fight against!");
            return;
        }

        EmbedBuilder builder = Config.getDefaultEmbed();
        String author = event.getAuthor().getAsTag();
        builder.addField(author + " x " + args, "A fight breaks out between " + author + " and " + args, false);

        event.getTextChannel().sendMessage(builder.build()).queue(m -> new Fight(m, author, args));
    }

    static class Fight {

        private static String[] abilities = {"Little Flower", "Jajanken", "Lightning", "punch", "Chapter 7 Bankruptcy", "Ripper Cyclotron", "Rising Sun",
                "100-Type Guanyin Bodhisattva"};

        private Message fightMessage;
        private Fighter fighter1;
        private Fighter fighter2;

        private Fighter currentFighter;
        Random random = new Random();

        public Fight(Message fightMessage, String fighter1, String fighter2) {
            this.fightMessage = fightMessage;
            this.fighter1 = new Fighter(fighter1);
            this.fighter2 = new Fighter(fighter2);

            Timer t = new Timer();

            if (random.nextBoolean()) {
                currentFighter = this.fighter1;
            } else {
                currentFighter = this.fighter2;
            }

            t.schedule(new TimerTask() {
                @Override
                public void run() {
                       doRound();
                }
            }, 1000);
        }

        private void doRound() {

            if (random.nextBoolean()) {
                if (random.nextInt(10) < 7) {
                    fighter2.damage(random.nextInt(20) + 10);

                }
            }
        }

        private void reply(MessageEmbed embed) {
            fightMessage.editMessage(embed).queue();
        }

        private MessageEmbed getNextEmbed(String attack) {
            return Config.getDefaultEmbed()
                    .setTitle(attack)
                    .addField(fighter1.name, fighter1.hp + " hp", true)
                    .addField(fighter2.name, fighter2.hp + " hp", true).build();
        }

        static class Fighter {
            private final String name;
            private int hp;

            public Fighter(String name) {
                this.name = name;
                this.hp = 100;
            }

            public void damage(int dmg) {
                hp -= dmg;
            }

            public String getName() {
                return name;
            }

            public int getHp() {
                return hp;
            }
        }
    }
}

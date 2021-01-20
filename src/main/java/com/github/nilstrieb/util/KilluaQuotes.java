package com.github.nilstrieb.util;

import java.util.Random;

public class KilluaQuotes {

    private static final Random random = new Random();
    private static final String[] quotes = {
            "When I say it doesn't hurt me, that means I can bear it.",
            "Gon, you are light itself. Sometimes you're too bright and I can't look at you... But can I still stay by your side?",
            "People only find me interesting because they can't tell whether I'm serious or not.",
            "I'm so tired of killing, I just want to be a kid. Hanging out, doing stuff with Gon. That's it.",
            "Move, and you die. Use Nen, or utter one word, and you die. Now close you eyes... You see what happens when you break promises? Open your eyes again and look at me in the mirror. Listen. Don't you ever show your scummy face to us ever again. This is a promise you will keep.",
            "If I ignore a friend I have the ability to help, wouldn't I be betraying him?",
            "Who wants to have their life planned out for them.",
            "Not killing people is really hard. Clean living is tough.",
            "Assassination — It's the family trade. We all take it up. My folks see me as an exceptional prospect. But I don't see that I should have to live up to their expectations.",
            "Gon, let's get in front. Yeah, and it'll get us away from Hisoka. He's killed, and wants to kill again. The fog will make a great cover. You're wondering how I know. Fact is, I'm like him. I can smell it in him.",
            "Gon's suffering right now! Someone who meant a lot to him was ruthlessly altered! Even his mind was changed. As we speak, Gon's facing against the person responsible! If you up without warning, looking like that, he won't be able to hold it together. So you have to promise to call him by his name first! That's my one condition! Let him know you're okay. He was so worried about you, up to the minute we came in here. Give him some peace of mind. Please. Put his mind at ease. Only you, Palm, can do that right now. Nobody else can reach him. Not me. I can't help him anymore!",
            "Nanika, will you forgive me for being a bad big brother?",
            "We were complacent. Too confident of our powers. Both of us combined were worth less than a one-armed Kite. That was his assessment... That's the reality. If Kite was by himself, this wouldn't have happened. We were fools.",
            "Assassination — It’s the family trade. We all take it up. My folks see me as an exceptional prospect. But I don’t see that I should have to live up to their expectations.",
            "We didn’t do anything.. our enemies did"
    };

    public static String getRandomQuote() {
        return quotes[random.nextInt(quotes.length)];
    }

    public static String[] getAllQuotes() {
        return quotes;
    }
}

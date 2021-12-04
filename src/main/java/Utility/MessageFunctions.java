package Utility;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class MessageFunctions {
    public static MessageEmbed makeEmbed(String title, String msg, Color color) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.addField(title, msg, false);
        builder.setColor(color);
        return builder.build();
    }
}

package CommandList;

import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

import java.util.ArrayList;

public class Help implements BlankCommand {

    String name = "help";
    String description = "The DragonBot 3 Help Command";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;

    public Help() {

    }

    public Help(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new Help(e);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<Option> getParameters() {
        return this.parameters;
    }

    @Override
    public void run() {
        System.out.println("Hello World!");

        this.e.reply("Hello World!").queue();
    }
}

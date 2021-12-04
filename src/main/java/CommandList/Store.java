package CommandList;

import Utility.Inventory;
import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;

public class Store implements BlankCommand {

    String name = "setstore";
    String description = "Sets which store is active";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;
    public static Inventory storeInv;

    public Store() {

    }

    public Store(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new Store(e);
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
        if (storeInv != null) {
            e.replyEmbeds(storeInv.displayInventory().build()).queue();
        }
        else {
            e.reply("There is currently no store set.");
        }
    }
}

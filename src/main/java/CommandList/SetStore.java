package CommandList;

import Utility.Inventory;
import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;

public class SetStore implements BlankCommand {

    String name = "setstore";
    String description = "Sets which store is active";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;

    public SetStore() {
        this.parameters.add(new Option(
                OptionType.INTEGER,
                "storename",
                "Name of the store to be used",
                true));
    }

    public SetStore(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new SetStore(e);
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
        String storeName = e.getOption("storename").getAsString();
        Inventory i = Inventory.loadInventory("store" + storeName);
        if (i != null) {
            Store.storeInv = i;
        }
        else {
            Store.storeInv = new Inventory("store" + storeName);
        }
        e.reply("Store now set to " + storeName).queue();
    }
}

package CommandList;

import Utility.Item;
import Utility.MessageFunctions;
import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.awt.*;
import java.util.ArrayList;

public class RemoveItem implements BlankCommand {

    String name = "removeitem";
    String description = "Deletes item from the list";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;

    public RemoveItem() {
        this.parameters.add(new Option(
                OptionType.STRING,
                "itemname",
                "Name of the item to add",
                true));
    }

    public RemoveItem(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new RemoveItem(e);
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
        String name = e.getOption("itemname").getAsString();
        Item i;
        try {
            i = ItemList.allItems.getItem(Integer.parseInt(name));
        }
        catch (Exception e) {
            i = ItemList.allItems.getItem(name);
        }
        if (i != null) {
            ItemList.allItems.removeItem(i);
            ItemList.allItems.saveInventory();
            e.reply("Item removed: " + i.getName()).queue();
        }
        else {
            e.reply("Item not found.").queue();
        }
    }
}

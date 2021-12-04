package CommandList;

import Utility.Item;
import Utility.MessageFunctions;
import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.awt.*;
import java.util.ArrayList;

public class AddItem implements BlankCommand {

    String name = "additem";
    String description = "Add new item to item list";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;

    public AddItem() {
        this.parameters.add(new Option(
                OptionType.INTEGER,
                "itemtype",
                "0 - Armor, 1 - Weapon, 2 - Other",
                true));

        this.parameters.add(new Option(
                OptionType.STRING,
                "itemname",
                "Name of the item to add",
                true));

        this.parameters.add(new Option(
                OptionType.STRING,
                "itemdesc",
                "Item's description",
                true));
    }

    public AddItem(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new AddItem(e);
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
        Item i = new Item();
        i.setName(e.getOption("itemname").getAsString());
        i.setDescription(e.getOption("itemdesc").getAsString());
        i.setQuantity(1);
        i.setType((int) e.getOption("itemtype").getAsLong());

        ItemList.allItems.addItem(i);
        ItemList.allItems.saveInventory();

        e.reply("Created new item: " + i.getName()).queue();
    }
}

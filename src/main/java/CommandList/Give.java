package CommandList;

import Utility.Inventory;
import Utility.Item;
import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;

public class Give implements BlankCommand {

    String name = "give";
    String description = "Gives item to player.";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;

    public Give() {
        this.parameters.add(new Option(
                OptionType.USER,
                "receiver",
                "Player to give item to",
                true));
        this.parameters.add(new Option(
                OptionType.STRING,
                "itemname",
                "Item's name or index number",
                true));
    }

    public Give(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new Give(e);
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
        System.out.println("adding item to " + e.getOption("receiver").getAsUser().getId());
        Inventory inv = InventoryCmd.getInventory(e.getOption("receiver").getAsUser().getId());
        String name = e.getOption("itemname").getAsString();
        Item i;
        try {
            System.out.println("Trying index method...");
            i = ItemList.allItems.getItem(Integer.parseInt(name));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Trying name method");
            i = ItemList.allItems.getItem(name);
        }
        if (i != null) {
            inv.addItem(i);
            e.reply("Item added.").queue();
        }
        else {
            e.reply("Error finding item.").queue();
        }
    }
}

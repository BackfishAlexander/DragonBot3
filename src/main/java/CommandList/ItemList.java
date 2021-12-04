package CommandList;

import Utility.Inventory;
import Utility.Option;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.ArrayList;

public class ItemList implements BlankCommand {

    String name = "itemlist";
    String description = "Lists currently available items and their ids";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;
    static Inventory allItems;

    public ItemList() {
        allItems = Inventory.loadInventory("inventoryList");
        if (allItems == null) {
            allItems = new Inventory("inventoryList");
        }
    }

    public ItemList(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new ItemList(e);
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
        Inventory i = allItems;
        EmbedBuilder builder = i.displayInventory();
        builder.setTitle("Item List");
        e.replyEmbeds(builder.build()).queue();
    }
}

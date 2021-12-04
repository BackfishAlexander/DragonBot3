package CommandList;

import Utility.Inventory;
import Utility.MessageFunctions;
import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.awt.*;
import java.util.ArrayList;

public class InventoryCmd implements BlankCommand {
    String name = "inventory";
    String description = "Checks player's inventory";
    ArrayList<Option> parameters = new ArrayList<Option>();
    SlashCommandEvent e;

    public static ArrayList<Inventory> loadedInventories = new ArrayList<Inventory>();

    public InventoryCmd() {

    }

    public InventoryCmd(SlashCommandEvent e) {
        this.e = e;
        new Thread(this).start();
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new InventoryCmd(e);
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
        return parameters;
    }

    public static Inventory getInventory(String name) {
        for (Inventory i : loadedInventories) {
            if (i.getUniqueName().equals(name)) {
                return i;
            }
        }
        Inventory i = Inventory.loadInventory(name);
        if (i == null) {
            Inventory newInv = new Inventory(name);
            System.out.println("Adding inventory to memory...");
            loadedInventories.add(newInv);
            return newInv;
        } else {
            System.out.println("Adding inventory to memory...");
            loadedInventories.add(i);
            return i;
        }
    }

    @Override
    public void run() {
        String id = e.getUser().getId();
        Inventory i = getInventory(id);

        System.out.println("Getting inventory id of " + id);

        //e.replyEmbeds(MessageFunctions.makeEmbed(
         //       String.format("%s's Inventory", e.getMember().getNickname()),
         //       String.format("Inventory contains %d items.", i.getInventorySize()),
          //      Color.CYAN)).queue();

        e.replyEmbeds(i.displayInventory().setTitle(
                String.format("%s's Inventory", e.getMember().getNickname())).build()).queue();
    }
}

package Utility;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class Inventory implements Serializable {
    ArrayList<Item> itemList;
    String uniqueName; //Must be unique
    int gold;
    int silver;
    int copper;

    public Inventory(String uniqueName) {
        itemList = new ArrayList<Item>();
        this.uniqueName = uniqueName;

        gold = -1;
        silver = -1;
        copper = -1;
    }
    public void saveInventory() {
        try {
            System.out.println("Saving inventory...");
            FileOutputStream fileOut = new FileOutputStream(this.uniqueName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
        }
        catch (Exception e) {
            System.out.println("Error saving Inventory file: " + this.uniqueName);
        }
    }
    public static Inventory loadInventory(String fileName) {
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            return (Inventory) oi.readObject();
        }
        catch (Exception e) {
            System.out.println("Inventory not found.");
            e.printStackTrace();
            return null;
        }
    }

    public void addItem(Item i) {
        this.itemList.add(i);
    }

    public void removeItem(Item i) {
        this.itemList.remove(i);
    }

    public Item getItem(String s) {
        int c = 0;
        Item result = new Item();
        for (Item i : itemList) {
            if (i.getName().toLowerCase().contains(s.toLowerCase())) {
                c++;
                result = i;
            }
        }
        if (c == 1) {
            return result;
        }
        else {
            return null;
        }
    }

    public Item getItem(int i) {
        return itemList.get(i);
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public int getInventorySize() {
        return itemList.size();
    }

    public EmbedBuilder displayInventory() {
        StringBuilder sb = new StringBuilder();
        for (Item i : this.itemList) {
            if (i.type == 0) {
                sb.append("\uD83D\uDC55");
            }
            else if (i.type == 1) {
                sb.append("\uD83D\uDDE1");
            }
            else {
                sb.append("\uD83D\uDCE6");
            }
            sb.append(" ");

            if (i.quantity > 1) {
                sb.append("| ");
                sb.append(i.quantity);
                sb.append(" |");
            }

            sb.append(" ");
            sb.append(i.name);
            sb.append(": ");
            sb.append(i.description);
            sb.append("\n");
        }

        StringBuilder currency = new StringBuilder();

        if (gold > 0) {
            currency.append(String.format("Gold: %d\n", gold));
        }
        if (silver > 0) {
            currency.append(String.format("Silver: %d\n", silver));
        }
        if (copper > 0) {
            currency.append(String.format("Copper: %d\n", copper));
        }

        EmbedBuilder builder = new EmbedBuilder();
        if (!(gold < 0 && silver < 0 && copper < 0)) {
            builder.addField("Currency", currency.toString(), false);
        }
        if (itemList.size() > 0) {
            builder.addField("Items", sb.toString(), false);
        }
        else {
            builder.addField("This inventory is empty.", "", true);
        }

        return builder;
    }
}

package Utility;

import java.awt.*;
import java.io.Serializable;

public class Item implements Serializable {
    String name;
    String description;
    int quantity;
    int type;

    //Type:
    //0: Armor    👕
    //1: Weapon   🗡
    //2: Other    📦


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

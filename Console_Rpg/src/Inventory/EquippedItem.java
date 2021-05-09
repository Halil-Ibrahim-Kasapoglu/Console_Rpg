package Inventory;

import Items.Armor;
import Items.Item;
import Items.Weapon;

public enum EquippedItem{
    Weapon(0),
    Armor(1);

    private int id;
    public int getId() {
        return id;
    }

    EquippedItem(int id){
        this.id = id;
    }

    public static EquippedItem type(Item item){
        if (item instanceof Weapon){
            return EquippedItem.Weapon;
        }else {
            return EquippedItem.Armor;
        }
    }

};
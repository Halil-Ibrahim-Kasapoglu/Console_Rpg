package Inventory;

import Items.Armor;
import Items.Item;
import Items.Weapon;
import java.util.ArrayList;
import java.util.Arrays;


public class Inventory {

    private ArrayList<Item> itemArrayList;
    private Item[] equippedItems;

    public Item[] getEquippedItems() {
        return equippedItems;
    }

    public Item getEquippedItem(EquippedItem item) {
        return equippedItems[item.getId()];
    }

    public Inventory(){

        itemArrayList = new ArrayList<Item>();
        equippedItems = new Item[2];
    }

    public void AddItem(Item item){
        itemArrayList.add(item);
        System.out.println(item.getName() + " added to inventory");
    }
    public void RemoveItem(int arrayIndex){
        // not implemented
    }
    public void Equip(Item item){
        if (item instanceof Weapon){
            equippedItems[EquippedItem.Weapon.getId()] = item;
        }else if (item instanceof Armor){
            equippedItems[EquippedItem.Armor.getId()] = item;
        }
    }
    public void Unequip(EquippedItem item){
        equippedItems[item.getId()] = null;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "itemArrayList=" + itemArrayList +
                ", equippedItems=" + Arrays.toString(equippedItems) +
                '}';
    }
}

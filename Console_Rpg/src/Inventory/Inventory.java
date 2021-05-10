package Inventory;

import Items.Armor;
import Items.Item;
import Items.Weapon;
import Manager.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class Inventory implements Serializable {

    private ArrayList<Item> itemArrayList;
    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    private Item[] equippedItems;

    public Item[] getEquippedItems() {
        return equippedItems;
    }

    public Item getEquippedItem(EquippedItem item) {
        return equippedItems[item.getId()];
    }

    public boolean isContains(Item item){
        for (Item it : itemArrayList){
            if (item.getId() == it.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean isEquipped(Item item){
        for (Item equippedItem : equippedItems){
            if (equippedItem == null) continue;
            if (item.getId() == equippedItem.getId()){
                return true;
            }
        }
        return false;
    }
    public void sellItem(Item item) {
        if (isEquipped(item)){
            Unequip(EquippedItem.type(item));
        }
        itemArrayList.remove(item);
        UserManager.Master().getActivePlayer().IncrementMoney(item.getMarketPrice());
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
        System.out.println(item.getName() + " equipped");
        if (item instanceof Weapon){
            equippedItems[EquippedItem.Weapon.getId()] = item;
        }else if (item instanceof Armor){
            equippedItems[EquippedItem.Armor.getId()] = item;
        }
    }
    public void Unequip(EquippedItem item){
        System.out.println(equippedItems[item.getId()].getName() + " unequipped");
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

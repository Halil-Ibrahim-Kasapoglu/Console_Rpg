package Inventory;

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

};
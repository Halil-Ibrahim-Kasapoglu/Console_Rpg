package Consumables;

import Manager.UserManager;

public class ConsumableFood  extends Consumable implements IConsumable{

    private int healthIncrement;

    public ConsumableFood(String name , int healthIncrement){
        super(name);
        this.healthIncrement = healthIncrement;
        this.setMarketPrice(healthIncrement * 5);
    }

    @Override
    public void Consume() {
        UserManager.Master().getActivePlayer().getHealth().restoreHealth(healthIncrement);
    }

    @Override
    public String toString() {
        return "ConsumableFood{" +
                "healthIncrement=" + healthIncrement +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", marketPrice=" + marketPrice +
                '}';
    }
}

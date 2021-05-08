package Player;

import Entity.Enemy;
import Inventory.Inventory;
import Manager.SceneManager;
import Scenes.DeathScene;
import Utility.UtilityHelper;

public class Player {

    private boolean alive;
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    PlayerHealth health;
    public PlayerHealth getHealth() {
        return health;
    }

    private int level;
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }

    private double xp;
    public double getXp() {
        return xp;
    }

    public void IncrementXp(double amount){
        System.out.println("+ "+amount + " xp");
        xp += amount;
        while (xpFunction(getLevel()) <= xp){
            levelUp();
        }
    }

    // total xp needed to reach level n;
    // might be broken
    private double xpFunction(int level){
        return 10*level + (level / (1 + Math.log(2))) * (Math.pow(2,Math.log(level)));
    }
    public void levelUp(){
        setLevel(getLevel()+1);
        System.out.println("!!! Leveled up to " + level + " !!!");
        health.restoreHealthFully();
    }


    private double money;
    public double getMoney() {
        return money;
    }

    private Inventory inventory;
    public Inventory getInventory() {
        return inventory;
    }

    public Player(){

        inventory = new Inventory();
        health = new PlayerHealth( 100);
        alive = true;
        xp = 0;
        money = 0;
        level = 1;
    }

    public void IncrementMoney(double amount)
    {
        System.out.println("+ "+amount + " money");
        this.money += amount;
    }

    public void payMoney(double amount){
        double pre = money;
        money -= amount;
        money = Math.max(0 , money);
        System.out.println("- "+(pre - money)+ " money");
    }

    public void GotHit(int damage , Enemy enemy){
        health.applyDamage(damage);
        if (health.getCurrentHealth() <= 0){
            Die("Oh dear! You have been slaughtered by " + enemy.name);
        }
    }

    public void Die(String deathMessage){
        setAlive(false);
        SceneManager.Master().emptyAndPushScene(new DeathScene(deathMessage));
    }
    public void Die(){
        setAlive(false);
        SceneManager.Master().emptyAndPushScene(new DeathScene());
    }
    public void Revive(){
        double revivalCost = 10.0;
        payMoney(revivalCost);
        setAlive(true);
        health.restoreHealthFully();
    }

    @Override
    public String toString() {
        return "Player{" +
                "health=" + health +
                ", level=" + level +
                ", nextLevel at= "+ UtilityHelper.Decimal2(xpFunction(level)) + " xp " +
                ", xp=" + UtilityHelper.Decimal2(xp) +
                ", money=" + UtilityHelper.Decimal2(money) +
                ", inventory=" + inventory +
                '}';
    }
}

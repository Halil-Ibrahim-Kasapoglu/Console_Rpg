package Entity;

import Manager.RandomManager;
import Manager.UserManager;

public class Enemy extends Entity{

    private int damage;

    public int getDamage() {
        return damage;
    }

    private int health;

    public int getHealth() {
        return health;
    }

    private int level;
    public int getLevel() {
        return level;
    }

    private static String[] enemyNames = new String[]{"Dark Lord" , "Goblin" , "Nightmare Johny" , "Soul of Revengeful Nature", "A Jar of Bugs" , "Canavar" , "Kangal"};

    public Enemy(String name, int damage , int health , int level){
        super();
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.level = level;
    }

    public static Enemy generateRandomEnemy(){

        String name = enemyNames[RandomManager.Random(0 , enemyNames.length - 1)];
        int level = Math.max(1 , UserManager.Master().getActivePlayer().getLevel() + RandomManager.Random(-5 , 5));
        int health = level * 5;
        int damage = level;
        return new Enemy(name , damage , health , level);
    }

    public void getDamage(double amount){
        health -= amount;
        if (health <= 0){
            health = 0;
            this.alive = false;
        }
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "damage=" + damage +
                ", health=" + health +
                ", level=" + level +
                '}';
    }
}

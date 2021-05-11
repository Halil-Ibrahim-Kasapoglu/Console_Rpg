package Entity;

import Manager.FileManager;
import Manager.RandomManager;
import Manager.UserManager;

import java.util.ArrayList;

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

    private final static ArrayList<String> enemyNames = FileManager.ReadFileAsArray("data/entity/enemy/noun.txt");

    public Enemy(String name, int damage , int health , int level){
        super();
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.level = level;
    }

    public static Enemy generateRandomEnemy(){

        String name = enemyNames.get(RandomManager.Random(0 , enemyNames.size() - 1));
        int level = Math.max(1 , UserManager.Master().getActivePlayer().getLevel() + RandomManager.Random(-5 , 5));
        int health = level * 5;
        int damage = level;
        return new Enemy(name , damage , health , level);
    }

    public int getRewardXp(){
        return damage * 2 + level;
    }
    public int getRewardPrice(){
        return level * 5;
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

package Items;

import Manager.RandomManager;
import Manager.UserManager;
import Utility.UtilityHelper;
import jdk.jshell.execution.Util;

import java.util.Random;


public class Weapon extends Item{

	static final String[] weaponNames = new String[]{"Dagger" , "Sword" , "Mace" , "Axe" , "Spoon"};
	static final String[] weaponAdj = new String[]{"Holy" , "Unstoppable" , "World Destroyer" , "Gold" , "Silver" , "Bronze", "Cursed"};

	private double damage;

	public double getDamage() {
		return damage;
	}

	private int level;

	private Random generator = new Random();

	public Weapon(String name , double damage , int level){
		super();
		this.name = name;
		this.damage = damage;
		this.level = level;
		setMarketPrice(Item.defineMarketPrice(level,damage));
	}

	public String toString(){

		String result = "";
		result += "-----\n"
		+ "Items.Weapon Name : " + this.name + "\n"
		+ "Level : " + this.level + "\n"
		+ "Damage : " + UtilityHelper.Decimal2(this.damage) + "\n"
		+ "Market Price :" + UtilityHelper.Decimal2(this.marketPrice) + "\n"
		+ "-------";

		return result;
	}

	public static Weapon GenerateRandomWeapon(){

		Random random = new Random();

		int level = Item.defineLevel();
		double damage = Item.defineProperty(level);
		String name = Item.GenerateRandomItemName(weaponAdj , weaponNames);

		return new Weapon(name, damage , level);
	}



}
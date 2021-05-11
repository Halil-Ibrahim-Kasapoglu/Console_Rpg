package Items;

import Manager.FileManager;
import Manager.RandomManager;
import Manager.UserManager;
import Utility.UtilityHelper;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Random;


public class Weapon extends Item{

	static final ArrayList<String> weaponAdj = FileManager.ReadFileAsArray("data/item/weapon/adj.txt");
	static final ArrayList<String> weaponNames = FileManager.ReadFileAsArray("data/item/weapon/noun.txt");

	private double damage;
	public double getDamage() {
		return damage;
	}

	private int level;

	public Weapon(String name , double damage , int level){
		super(name);
		this.damage = damage;
		this.level = level;
		setMarketPrice(ItemManager.defineMarketPrice(level,damage));
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

		int level = ItemManager.defineLevel();
		double damage = ItemManager.defineProperty(level);
		String name = ItemManager.GenerateRandomItemName(weaponAdj , weaponNames);

		return new Weapon(name, damage , level);
	}

	@Override
	public double[] getAttributes() {
		return new double[]{this.damage};
	}

	@Override
	public String[] getAttributesString() {
		return new String[]{"damage"};
	}


}
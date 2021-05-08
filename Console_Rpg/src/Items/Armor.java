package Items;

import Utility.UtilityHelper;

import java.util.Random;

public class Armor extends Item{

	static final String[] armorNames = new String[]{"Great Wall Of China" , "Plate" , "Vest" , "Gambeson" , "Helmet" , "Aegis" , "T-shirt"};
	static final String[] armorAdj = new String[]{"Ultra" , "Unstoppable" , "Bullet Proof" , "Gold" , "Silver" , "Bronze", "Cursed" , "Celestial" , "Damaged","Dark"};

	private double armor;

	public double getArmor() {
		return armor;
	}

	private int level;
	private String name;

	public Armor(String name , double armor , int level){
		super();
		this.name = name;
		this.armor = armor;
		this.level = level;
		setMarketPrice(Item.defineMarketPrice(this.level , this.armor));
	}

	public String toString(){

		String result = "";
		result += "-----\n"
		+ "Items.Armor Name : " + this.name + "\n"
		+ "Level : " + this.level + "\n"
		+ "Armor : " + UtilityHelper.Decimal2(this.armor) + "\n"
		+ "Market Price :" + UtilityHelper.Decimal2(this.marketPrice) + "\n"
		+ "-------";

		return result;
	}

	public static Armor GenerateRandomArmor(){

		int level = Item.defineLevel();
		double armor = Item.defineProperty(level);
		String name = Items.Item.GenerateRandomItemName(armorAdj , armorNames);

		return new Armor(name, armor , level);
	}

}
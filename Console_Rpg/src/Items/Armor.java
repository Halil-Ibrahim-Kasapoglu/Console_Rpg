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

	public Armor(String name , double armor , int level){
		super(name);
		this.armor = armor;
		this.level = level;
		setMarketPrice(ItemManager.defineMarketPrice(this.level , this.armor));
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

		int level = ItemManager.defineLevel();
		double armor = ItemManager.defineProperty(level);
		String name = ItemManager.GenerateRandomItemName(armorAdj , armorNames);

		return new Armor(name, armor , level);
	}

	@Override
	public double[] getAttributes() {
		return new double[]{this.armor};
	}

	@Override
	public String[] getAttributesString() {
		return new String[]{"armor"};
	}
}
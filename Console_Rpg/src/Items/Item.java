package Items;

import Manager.RandomManager;
import Manager.UserManager;
import Utility.UtilityHelper;

import java.util.Random;

public class Item{

	protected String name;
	protected int id;

	protected double marketPrice;
	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Item(){
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public static String GenerateRandomItemName(String[] adjectives , String nouns[]){

		int nounsIndex = RandomManager.Random(0,nouns.length-1);
		int adjectiveIndex = RandomManager.Random(0,adjectives.length-1);

		String name = "";
		name += adjectives[adjectiveIndex] + " " + nouns[nounsIndex];

		return name;
	}

	public static int defineLevel(){
		int userLevel = UserManager.Master().getActivePlayer().getLevel();
		double gaussian = RandomManager.nextGaussian();
		double spread = 5;
		int itemLevel = (int)(userLevel * 1.0 + gaussian * spread);
		itemLevel = Math.max(1 , itemLevel);
		return itemLevel;
	}

	public static double defineProperty(int level){
		double propertyLimit = 100000;
		double property = propertyLimit/(1.0f + Math.pow(Math.exp(1) , -Math.log(level/propertyLimit)));
		property = UtilityHelper.Round2(property);
		return property;
	}
	public static double defineMarketPrice(int level , double prop){
		return UtilityHelper.Round2 ((level + prop) / 100.0f);
	}

}
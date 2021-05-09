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

	public Item(String name){
		this.id = ItemManager.newItemId();
		this.name = name;
	}

	public Item(){
		this.id = ItemManager.newItemId();
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
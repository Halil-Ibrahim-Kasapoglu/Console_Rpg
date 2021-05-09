package Items;

import Manager.RandomManager;
import Manager.UserManager;
import Utility.UtilityHelper;

public class ItemManager {

    private static ItemManager _master;
    public static ItemManager Master(){
        if (_master == null){
            _master = new ItemManager();
        }
        return _master;
    }

    static int lastItemId = 0;
    public static int newItemId(){
        lastItemId += 1;
        return lastItemId;
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

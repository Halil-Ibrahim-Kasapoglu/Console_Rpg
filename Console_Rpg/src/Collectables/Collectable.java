package Collectables;

import Manager.FileManager;
import Manager.RandomManager;

import java.util.ArrayList;
import java.util.Random;

public class Collectable {

    private String category;
    public String getCategory() {
        return category;
    }

    private String name;
    public String getName() {
        return name;
    }

    private int rarityLevel;
    public int getRarityLevel() {
        return rarityLevel;
    }

    public String getRarity() {
        return  FileManager.ReadFileAsArray("data/collectable/collectableRarity.txt").get(rarityLevel + 2);
    }

    private double marketPrice;
    public double getMarketPrice() {
        return marketPrice;
    }

    private String response;
    public String getResponse() {
        return response;
    }

    public Collectable(String name,  int rarityLevel , double marketPrice , String response , String category){
        this.name = name;
        this.rarityLevel = rarityLevel;
        this.marketPrice = marketPrice;
        this.category = category;
        this.response = response;
    }

    private static int randomRarity(int lower , int chances[]){
        int total = 0;
        for (int chance: chances){
            total += chance;
        }

        int randomIndex = RandomManager.Random(1 , total);
        int rarity = lower;
        for (int i = 0; i < chances.length; i ++){
            if (chances[i] > randomIndex){
                rarity += i;
                break;
            }else {
                randomIndex -= chances[i];
            }
        }
        return rarity;
    }

    static Collectable RandomCollectableFromData(ArrayList<String> noun_data, ArrayList<String> adjective_data , ArrayList<String> responses , String category){

        String noun = "";
        String adjective = "";

        int nounRarity = randomRarity(0 , new int[]{7,6,5,4,3,2,1});
        int adjectiveRarity = randomRarity(-2, new int[]{3,5,10,5,3});

        ArrayList<String> nounOptions = new ArrayList<String>();
        ArrayList<String> adjOptions = new ArrayList<String>();

        for (String row : noun_data){
            String stripped = row.strip();
            String[] parsed = stripped.split(",");
            if (Integer.parseInt(parsed[1]) == nounRarity) {
                nounOptions.add(parsed[0]);
            }
        }

        for (String row : adjective_data){
            String stripped = row.strip();
            String[] parsed = stripped.split(",");
            if (Integer.parseInt(parsed[1]) == adjectiveRarity) {
                adjOptions.add(parsed[0]);
            }
        }

        noun = nounOptions.get(RandomManager.Random(0 , nounOptions.size()-1));
        adjective = adjOptions.get(RandomManager.Random(0 , adjOptions.size()-1));

        int rarityLevel = nounRarity + adjectiveRarity;
        double randomizedMarketPrice = defineMarketPrice(rarityLevel);

        return new Collectable(adjective + " " + noun , rarityLevel, randomizedMarketPrice , responses.get(rarityLevel + 2) , category );

    }

    private static double defineMarketPrice(int rarityLevel){

        // returns a double between 1.0 -> 2.0
        double varietyConstant = RandomManager.Random(100, 200) / 100.0f;

        // returns 2 ^ rarity
        double basePrice = Math.pow(2.0 , Math.abs(rarityLevel));

        // negativity determiner
        double rarityDirection = rarityLevel;

        double marketPrice = basePrice * rarityDirection * varietyConstant;

        return Math.round(marketPrice * 100) / 100.0;
    }

    public static Collectable RandomCollectable(){

        ArrayList<String> collectibleCategories = FileManager.ReadFileAsArray("data/collectable/collectableList.txt");
        int categoryId = RandomManager.Random(0 , collectibleCategories.size() - 1);
        String categoryStr = collectibleCategories.get(categoryId);
        ArrayList<String> responses = FileManager.ReadFileAsArray("data/collectable/"+categoryStr+"/"+categoryStr+"_responses.txt");

        ArrayList<String> noun_data = FileManager.ReadFileAsArray("data/collectable/"+categoryStr+"/"+categoryStr+".csv" , 1);
        ArrayList<String> adjective_data = FileManager.ReadFileAsArray("data/collectable/"+categoryStr+"/"+categoryStr+"_adj.csv" , 1);

        return RandomCollectableFromData(noun_data , adjective_data , responses , categoryStr);
    }

    @Override
    public String toString() {
        return "Collectable{" +
                "name='" + name + '\'' +
                ", rarityLevel=" + rarityLevel +
                ", marketPrice=" + marketPrice +
                ", response='" + response + '\'' +
                '}';
    }
}

package Scenes.StoreScenes;

import Consumables.ConsumableFood;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Manager.UserManager;
import Player.Player;
import Scenes.Scene;

public class FoodStoreScene extends Scene {

    ConsumableFood[] foodList = new ConsumableFood[]{
            new ConsumableFood("Carrot" , 5),
            new ConsumableFood("Magical Carrot" , 10),
            new ConsumableFood("Really Organic Carrot" , 15),
            new ConsumableFood("Greatest Carrot On Earth", 25),
    };

    @Override
    protected void InitializeSceneCommands() {
        sceneCommands.add(new KernelCommand("buy", new KernelRunnable() {@Override public void process(String[] params){BuyCommand(params[0]);}} , new String[]{"food-id"} ));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){BackCommand();}}));
        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        int row = 1;
        System.out.println("=======================");
        for (ConsumableFood food : foodList){
            System.out.println( "("+row+") " + food);
            row += 1;
        }
        System.out.println("=======================");

        //System.out.println("Nothing to see yet");

        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void BuyCommand(String idStr){
        int foodId;
        try {
            // minus 1 is because 1-index format in market display
            foodId = Integer.parseInt(idStr) - 1;
        }catch (NumberFormatException e){
            System.out.println("food id must be an integer");
            return;
        }

        // checking if index is available
        if ( !(foodId >= 0 && foodId <= foodList.length-1)){
            System.out.println("food id must be in range " + "(" +1 +","+foodList.length+")");
            return;
        }

        Player player = UserManager.Master().getActivePlayer();
        double price = foodList[foodId].getMarketPrice();
        if (player.getMoney() >= price){
            if (player.getHealth().hasFullHealth()){
                System.out.println("Yemek yemek istemiyorum zaten canim full");
            }else {
                System.out.println("Yemek " + foodList[foodId] + " alindi ve yendi");
                foodList[foodId].Consume();
                player.payMoney(price);
            }
        }else {
            System.out.println("Not enough money");
        }
    };
    private void BackCommand(){
        SceneManager.Master().popScene();
    }
}

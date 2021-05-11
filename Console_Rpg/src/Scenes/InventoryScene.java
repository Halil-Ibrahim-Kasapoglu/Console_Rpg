package Scenes;

import Inventory.Inventory;
import Items.Item;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Manager.UserManager;
import Scenes.InspectionScenes.ItemInspectScene;
import UI.MultipageView;
import Utility.UtilityHelper;

import java.util.ArrayList;

public class InventoryScene extends Scene{

    private  MultipageView itemView;

    // FIXME: 9.05.2021 allah rizasi icin bu sayfayi duzenleyin

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("inspect", new KernelRunnable() {@Override public void process(String[] params){InspectItem(params);}} , new String[]{"row"}));
        sceneCommands.add(new KernelCommand("page", new KernelRunnable() {
            @Override
            public void process(String[] params) {
                PageCommand(params);
            }
        },new String[]{"page"}));
        sceneCommands.add(new KernelCommand("quick_sell_all_unequipped", new KernelRunnable() {@Override public void process(String[] params){QuickSellAllUnequippedCommand();}}));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    public void OnSceneCreated() {
        super.OnSceneCreated();
        itemView = new MultipageView(5, rows());
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();

        itemView.setRows(rows());
        itemView.loadPage(itemView.getCurrentPage());
    }

    private ArrayList<String> rows(){
        ArrayList<String> rows = new ArrayList<String>();
        Inventory inventory = UserManager.Master().getActivePlayer().getInventory();
        for (Item item : inventory.getItemArrayList()){
            rows.add((inventory.isEquipped(item) ? "(+) " : "(o) ") + item.getName() );
        }
        return rows;
    }

    private Item GetItemFromInventory(int row){
        Inventory inventory = UserManager.Master().getActivePlayer().getInventory();
        int id = row - 1;
        return inventory.getItemArrayList().get(id);
    }

    private void InspectItem(String[] params){
        if (! UtilityHelper.isNumeric(params[0])){
            System.out.println("row param must be an integer");
            return;
        }
        int row = Integer.parseInt(params[0]);
        if (row < 1 || row > itemView.getRowCnt()){
             System.out.println("no such item in row: " + row);
             return;
        }
        SceneManager.Master().pushScene(new ItemInspectScene(GetItemFromInventory(row)));
    }

    private void QuickSellAllUnequippedCommand(){

        int totalItemSold = 0;
        double totalPrice = 0;
        Inventory inventory = UserManager.Master().getActivePlayer().getInventory();
        ArrayList<Item> itemsToSell = new ArrayList<Item>();

        for (Item item : inventory.getItemArrayList()){
            if (!inventory.isEquipped(item)){
                itemsToSell.add(item);
            }
        }
        for (Item item : itemsToSell){
            inventory.sellItem(item);
            totalPrice += item.getMarketPrice();
            totalItemSold += 1;
        }
        if (totalItemSold == 0){
            System.out.println("No unequipped item to sell");
            return;
        }
        System.out.println("Quick Sell Report");
        System.out.println("Item sold : " + totalItemSold);
        System.out.println("Total Output : " + UtilityHelper.Decimal2(totalPrice) + " money earned");

        OnSceneDisplayed();
    }

    private void PageCommand(String[] params){

        if (!UtilityHelper.isNumeric(params[0])){
            System.out.println("page param must be integer");
            return;
        }
        int page = Integer.parseInt(params[0]);
        System.out.println("Changing page to " + page);

        itemView.loadPage(page);
    }
}

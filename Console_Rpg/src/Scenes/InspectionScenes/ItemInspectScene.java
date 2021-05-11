package Scenes.InspectionScenes;

import Inventory.Inventory;
import Inventory.EquippedItem;
import Items.Armor;
import Items.Item;
import Items.Weapon;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.RandomManager;
import Manager.SceneManager;
import Manager.UserManager;
import Player.Player;
import Scenes.Scene;

import java.util.ArrayList;

public class ItemInspectScene extends Scene {

    private Item item;

    public ItemInspectScene(Item item){
        super();
        this.item = item;
    }

    @Override
    protected void LoadSceneCommands(){
        super.LoadSceneCommands();
        Inventory inventory = UserManager.Master().getActivePlayer().getInventory();
        if (inventory.isContains(item)){
            if (!inventory.isEquipped(item)){
                sceneCommands.add(new KernelCommand("equip", new KernelRunnable() {
                    @Override
                    public void process(String[] params) {
                        inventory.Equip(item);
                        OnSceneDisplayed();
                    }
                }));
            }else {
                sceneCommands.add(new KernelCommand("unequip", new KernelRunnable() {
                    @Override
                    public void process(String[] params) {
                        inventory.Unequip(EquippedItem.type(item));
                        OnSceneDisplayed();
                    }
                }));
            }

            sceneCommands.add(new KernelCommand("sell", new KernelRunnable() {
                @Override
                public void process(String[] params) {
                    inventory.sellItem(item);
                    OnSceneDisplayed();
                }
            }));
        }
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    public void OnSceneDraw() {
        super.OnSceneDraw();

        System.out.println("Inspecting Item " + item.getName());
        System.out.println(item);
    }
}

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

    // FIXME: 9.05.2021 beni de duzenleyin lutfen

    private Item item;

    public ItemInspectScene(Item item){
        super();
        this.item = item;
        LoadSceneCommands();
    }

    private void LoadSceneCommands(){
        sceneCommands.clear();
        Inventory inventory = UserManager.Master().getActivePlayer().getInventory();
        if (inventory.isContains(item)){

            if (!inventory.isEquipped(item)){
                sceneCommands.add(new KernelCommand("equip", new KernelRunnable() {
                    @Override
                    public void process(String[] params) {
                        inventory.Equip(item);
                        LoadSceneCommands();
                        OnLoad();
                    }
                }));
            }else {
                sceneCommands.add(new KernelCommand("unequip", new KernelRunnable() {
                    @Override
                    public void process(String[] params) {
                        inventory.Unequip(EquippedItem.type(item));
                        LoadSceneCommands();
                        OnLoad();
                    }
                }));
            }

            sceneCommands.add(new KernelCommand("sell", new KernelRunnable() {
                @Override
                public void process(String[] params) {
                    inventory.sellItem(item);
                    LoadSceneCommands();
                    OnLoad();
                }
            }));
        }
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){BackCommand();}}));
    }

    @Override
    protected void InitializeSceneCommands() {
        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Inspecting Item " + item.getName());
        System.out.println(item);
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void BackCommand(){
        SceneManager.Master().popScene();
    }

}

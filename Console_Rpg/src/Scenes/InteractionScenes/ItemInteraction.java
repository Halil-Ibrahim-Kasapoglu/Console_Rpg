package Scenes.InteractionScenes;

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

public class ItemInteraction extends Scene {

    private Item item;

    private boolean collected = false;
    private boolean equipped = false;
    private boolean sold = false;

    private Item generateItem(){
        int itemType = RandomManager.Random(0,1);
        if (itemType == 0){
            return Weapon.GenerateRandomWeapon();
        }else {
            return Armor.GenerateRandomArmor();
        }
    }

    @Override
    public void OnSceneCreated() {
        super.OnSceneCreated();
        item = generateItem();
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("You have found an item");
        System.out.println(item);
    }

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        if (!equipped && !sold)sceneCommands.add(new KernelCommand("equip", new KernelRunnable() {@Override public void process(String[] params){EquipCommand();}}));
        if (!collected && !sold)sceneCommands.add(new KernelCommand("collect", new KernelRunnable() {@Override public void process(String[] params){CollectCommand();}}));
        if (!equipped && !sold)sceneCommands.add(new KernelCommand("compare", new KernelRunnable() {
            @Override
            public void process(String[] params) {
                CompareCommand();
            }
        }));
        if (!sold && !collected)sceneCommands.add(new KernelCommand("quicksell", new KernelRunnable() {@Override public void process(String[] params){QuickSellCommand();}}));
        sceneCommands.add(new KernelCommand("leave", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    private void EquipCommand() {
        UserManager.Master().getActivePlayer().getInventory().AddItem(item);
        UserManager.Master().getActivePlayer().getInventory().Equip(item);
        equipped = true;
        collected = true;
        OnSceneDisplayed();
    }

    private void CollectCommand(){
        UserManager.Master().getActivePlayer().getInventory().AddItem(item);
        collected = true;
        OnSceneDisplayed();
    }

    private void QuickSellCommand() {
        UserManager.Master().getActivePlayer().IncrementMoney(item.getMarketPrice());
        sold = true;
        OnSceneDisplayed();
    }

    private void CompareCommand(){
        Player player = UserManager.Master().getActivePlayer();
        EquippedItem itemType = EquippedItem.type(item);
        Item currentlyEquipped = player.getInventory().getEquippedItem(itemType);
        if (currentlyEquipped == null){
            System.out.println("Uzerinde bisey yok bunu alman daha iyi olur");
            return;
        }
        System.out.println("Currently Equipped : " + currentlyEquipped.getAttributesString()[0] + ": " + currentlyEquipped.getAttributes()[0]);
        if (currentlyEquipped.getAttributes()[0] < item.getAttributes()[0]){
            System.out.println("Bu yeni buldugumuzda " + (item.getAttributes()[0] - currentlyEquipped.getAttributes()[0]) + " daha fazla " + item.getAttributesString()[0] + "var");
            System.out.println("Bunu kusanman daha iyi olur");
        }else if (currentlyEquipped.getAttributes()[0] == item.getAttributes()[0]){
            System.out.println(item.getAttributesString()[0] + " ikisindede ayni. Kusanmana gerek yok");
        }else {
            System.out.println("Uzerindeki daha iyi");
        }
    }


}

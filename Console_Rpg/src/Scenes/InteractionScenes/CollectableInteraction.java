package Scenes.InteractionScenes;

import Collectables.Collectable;
import Entity.Enemy;
import Items.Armor;
import Items.Item;
import Items.Weapon;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.RandomManager;
import Manager.SceneManager;
import Manager.UserManager;
import Scenes.Scene;

public class CollectableInteraction extends Scene {

    private Collectable collectable;
    private boolean sold = false;

    public CollectableInteraction(){
        super();
        this.collectable = generateCollectable();
    }

    private Collectable generateCollectable(){
        return Collectable.RandomCollectable();
    }

    @Override
    protected void InitializeSceneCommands() {

        sceneCommands.add(new KernelCommand("sell", new KernelRunnable() {@Override public void process(){SellCommand();}}));
        //sceneCommands.add(new KernelCommand("sell", new KernelRunnable() {@Override public void process(){AttackCommand();}}));
        sceneCommands.add(new KernelCommand("leave", new KernelRunnable() {@Override public void process(){LeaveCommand();}}));

        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("You have found a collectable\n" + collectable.getName());
        System.out.println("Rarity: " + collectable.getRarity() + " ( " + collectable.getRarityLevel() + " ) ");
        System.out.println("Market Price: " + collectable.getMarketPrice());
        System.out.println("Category :" + collectable.getCategory());
        System.out.println(collectable.getResponse());
        System.out.println("=======================");
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void SellCommand(){
        sold = true;
        UserManager.Master().getActivePlayer().IncrementMoney(collectable.getMarketPrice());
        sceneCommands.remove(0);
        Kernel.Master().DisplayAvailableCommands();
    }
    private void LeaveCommand(){
        SceneManager.Master().popScene();
    }
}

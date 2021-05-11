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

    private Collectable generateCollectable(){
        return Collectable.RandomCollectable();
    }

    @Override
    public void OnSceneCreated() {
        super.OnSceneCreated();
        this.collectable = generateCollectable();
    }

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        if (!sold) sceneCommands.add(new KernelCommand("sell", new KernelRunnable() {@Override public void process(String[] params){SellCommand();}}));
        sceneCommands.add(new KernelCommand("leave", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();

        System.out.println("You have found a collectable\n" + collectable.getName());
        System.out.println("Rarity: " + collectable.getRarity() + " ( " + collectable.getRarityLevel() + " ) ");
        System.out.println("Market Price: " + collectable.getMarketPrice());
        System.out.println("Category: " + collectable.getCategory());
        System.out.println(collectable.getResponse());
    }

    private void SellCommand(){
        sold = true;
        UserManager.Master().getActivePlayer().IncrementMoney(collectable.getMarketPrice());
        OnSceneDisplayed();
    }
}

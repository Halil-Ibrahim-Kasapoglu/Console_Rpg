package Scenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Scenes.StoreScenes.StoreScene;

public class HomeScene extends Scene{

    @Override
    protected void InitializeSceneCommands() {
        sceneCommands.add(new KernelCommand("travel", new KernelRunnable() {@Override public void process(){TravelCommand();}}));
        sceneCommands.add(new KernelCommand("inventory", new KernelRunnable() {@Override public void process(){InventoryCommand();}}));
        sceneCommands.add(new KernelCommand("store", new KernelRunnable() {@Override public void process(){StoreCommand();}}));
        sceneCommands.add(new KernelCommand("mainmenu", new KernelRunnable() {@Override public void process(){MainMenu();}}));
        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Adventure calling out to you");
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void TravelCommand(){
        SceneManager.Master().pushScene(new TravelScene());
    }
    private void InventoryCommand(){
        SceneManager.Master().pushScene(new InventoryScene());
    }
    private void StoreCommand(){
        SceneManager.Master().pushScene(new StoreScene());
    }
    private void MainMenu(){
        SceneManager.Master().popScene();
    }
}

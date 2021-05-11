package Scenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Scenes.Library.LibraryScene;
import Scenes.StoreScenes.StoreScene;

public class HomeScene extends Scene{

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("travel", new KernelRunnable() {@Override public void process(String[] params){TravelCommand();}}));
        sceneCommands.add(new KernelCommand("inventory", new KernelRunnable() {@Override public void process(String[] params){InventoryCommand();}}));
        sceneCommands.add(new KernelCommand("store", new KernelRunnable() {@Override public void process(String[] params){StoreCommand();}}));
        sceneCommands.add(new KernelCommand("library", new KernelRunnable() {@Override public void process(String[] params){LibraryCommand();}}));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("Adventure calling out to you");
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
    private void LibraryCommand() {SceneManager.Master().pushScene(new LibraryScene());}

}

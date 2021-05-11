package Scenes.StoreScenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Manager.UserManager;
import Scenes.MainMenuScene;
import Scenes.Scene;

public class StoreScene extends Scene {

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("foods", new KernelRunnable() {@Override public void process(String[] params){FoodCommand();}}));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("Welcome to store where you can buy some stuff");
    }

    private void FoodCommand(){
        SceneManager.Master().pushScene(new FoodStoreScene());
    }

}

package Scenes.StoreScenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Scenes.Scene;

public class FoodStoreScene extends Scene {

    @Override
    protected void InitializeSceneCommands() {
        //sceneCommands.add(new KernelCommand("foods", new KernelRunnable() {@Override public void process(){FoodCommand();}}));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(){BackCommand();}}));
        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Nothing to see yet");
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void BackCommand(){
        SceneManager.Master().popScene();
    }
}

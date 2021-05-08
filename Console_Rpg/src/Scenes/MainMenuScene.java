package Scenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;

public class MainMenuScene extends Scene {

    @Override
    protected void InitializeSceneCommands() {
        sceneCommands.add(new KernelCommand("play", new KernelRunnable() {@Override public void process(){PlayCommand();}}));
        sceneCommands.add(new KernelCommand("quit", new KernelRunnable() {@Override public void process(){QuitCommand();}}));
        super.InitializeSceneCommands();

    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Welcome To Console RPG");
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void PlayCommand(){
        SceneManager.Master().pushScene(new HomeScene());
    }

    private void QuitCommand(){
        SceneManager.Master().popScene();
    }


}
package Scenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;

public class MainMenuScene extends Scene {

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("play", new KernelRunnable() {@Override public void process(String[] params){PlayCommand();}}));
        sceneCommands.add(new KernelCommand("save_and_quit", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("Welcome To Console RPG");
    }

    private void PlayCommand(){
        SceneManager.Master().pushScene(new HomeScene());
    }

}
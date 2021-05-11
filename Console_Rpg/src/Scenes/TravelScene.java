package Scenes;

import Manager.FileManager;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.RandomManager;
import Manager.SceneManager;
import Manager.UserManager;
import Scenes.InteractionScenes.CollectableInteraction;
import Scenes.InteractionScenes.EnemyInteraction;
import Scenes.InteractionScenes.ItemInteraction;

import java.util.ArrayList;
import java.util.Random;

public class TravelScene extends Scene{

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("move", new KernelRunnable() {@Override public void process(String[] params){MoveCommand();}}));
        sceneCommands.add(new KernelCommand("home", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("Don't be shy. Take a step");
    }

    private void MoveCommand(){

        int event = RandomManager.Random(0,3);

        if (event == 0){
            ArrayList<String> walkingMassages = FileManager.ReadFileAsArray("data/other/walking_messages.txt");
            System.out.println(walkingMassages.get(RandomManager.Random(0 , walkingMassages.size() - 1)));
        }else if (event == 1){
            SceneManager.Master().pushScene(new ItemInteraction());
        }else if (event == 2){
            SceneManager.Master().pushScene(new EnemyInteraction());
        }else {
            SceneManager.Master().pushScene(new CollectableInteraction());
        }
    }

}

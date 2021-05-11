package Scenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Manager.UserManager;

public class DeathScene extends Scene{

    private String deathMessage;

    public DeathScene(){
        super();
        this.deathMessage = "YOU HAVE DIED";
    }
    public DeathScene(String deathMessage){
        super();
        this.deathMessage = deathMessage;
    }

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("revive", new KernelRunnable() {@Override public void process(String[] params){ReviveCommand();}}));
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println(deathMessage);
        System.out.println("Careful next time. Don't forget every death cost you");
    }

    private void ReviveCommand(){
        UserManager.Master().getActivePlayer().Revive();
        SceneManager.Master().emptyAndPushScene(new MainMenuScene());
    }
}

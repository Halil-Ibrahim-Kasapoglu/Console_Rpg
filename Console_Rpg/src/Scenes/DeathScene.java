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
    protected void InitializeSceneCommands() {
        sceneCommands.add(new KernelCommand("revive", new KernelRunnable() {@Override public void process(String[] params){ReviveCommand();}}));
        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(deathMessage);
        System.out.println("Careful next time. Don't forget every death cost you");
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

    private void ReviveCommand(){
        UserManager.Master().getActivePlayer().Revive();
        SceneManager.Master().emptyAndPushScene(new MainMenuScene());
    }
}

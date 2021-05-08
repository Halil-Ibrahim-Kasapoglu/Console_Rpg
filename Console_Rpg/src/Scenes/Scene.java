package Scenes;

import java.util.ArrayList;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;

public class Scene {

	protected ArrayList<KernelCommand> sceneCommands;

	public Scene(){

		sceneCommands = new ArrayList<KernelCommand>();
		InitializeSceneCommands();
		//OnLoad();
	}

	protected void InitializeSceneCommands(){
	}

	public void OnLoad(){
		Kernel.Master().SetAvailableCommands(sceneCommands);
	}
}
package Scenes;

import java.util.ArrayList;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.SceneManager;

public class Scene {

	protected ArrayList<KernelCommand> sceneCommands;

	public Scene() {

	}

	protected void LoadSceneCommands(){
		sceneCommands = new ArrayList<KernelCommand>();
	}

	public void OnSceneCreated(){
	}

	public void OnSceneDisplayed(){
		LoadSceneCommands();
		Kernel.Master().SetAvailableCommands(sceneCommands);

		DisplaySeparator();
		OnSceneDraw();
		DisplayAvailableCommands();
	}

	protected void OnSceneDraw(){

	}

	public void DisplaySeparator(){
		int separatorLenght = 32;
		String separator = "=";
		for (int i = 0; i < separatorLenght; i ++){
			System.out.print(separator);
		}
		System.out.println();
	}
	public void DisplayAvailableCommands(){
		DisplaySeparator();
		Kernel.Master().DisplayAvailableCommands();
		DisplaySeparator();
	}

	protected void DismissScene(){
		SceneManager.Master().popScene();
	}
}
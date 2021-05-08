package Manager;

import Manager.Kernel.Kernel;
import Scenes.Scene;
import java.util.Stack;


public class SceneManager{

	private static SceneManager _master;
	public static SceneManager Master() {
		if (_master == null) {
			_master = new SceneManager();
		}
		return _master;
	}

	private Stack<Scene> sceneStack;
	private Scene activeScene;


	public SceneManager(){
		sceneStack = new Stack<Scene>();
	}

	public void setActiveScene(Scene activeScene) {
		this.activeScene = activeScene;
		if (activeScene!=null) {
			Kernel.ClearConsole();
			activeScene.OnLoad();
		}
	}
	public Scene getActiveScene() {
		return activeScene;
	}


	public void emptyAndPushScene(Scene scene){
		while (!sceneStack.empty()){
			sceneStack.pop();
		}
		pushScene(scene);
	}

	public void pushScene(Scene scene){
		sceneStack.add(scene);
		setActiveScene(scene);
	}
	public void popScene(){
		if (sceneStack.empty()){
			System.out.println("scene stack empty you cannot pop");
		}else {
			sceneStack.pop();
			if (sceneStack.empty()){
				setActiveScene(null);
			}else {
				setActiveScene(sceneStack.peek());
			}
		}
	}
}

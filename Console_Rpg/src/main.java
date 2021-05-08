import java.util.Scanner;

import Manager.FileManager;
import Manager.Kernel.Kernel;
import Manager.SceneManager;
import Manager.UserManager;
import Player.Player;
import Scenes.MainMenuScene;

public class main{

	public static void main(String args[]){

		Scanner scanner = new Scanner(System.in);


		Player player = new Player();
		System.out.println(player);

		UserManager.Master().setActivePlayer(player);
		SceneManager.Master().pushScene(new MainMenuScene());

		String command = "";

		while (SceneManager.Master().getActiveScene() != null){

			command = Kernel.readCommand();

			Kernel.Master().ProcessCommand(command);
		}

		System.out.println("game terminated");
	}
}
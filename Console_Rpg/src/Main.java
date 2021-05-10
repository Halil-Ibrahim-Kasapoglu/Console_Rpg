import java.io.*;
import java.util.Scanner;

import Manager.FileManager;
import Manager.Kernel.Kernel;
import Manager.SceneManager;
import Manager.UserManager;
import Player.Player;
import Scenes.MainMenuScene;

public class Main{

	public static Player getPlayer(String path){
		Player player = new Player();
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			player = (Player) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e){
			System.out.println("File not generated yet");
			return new Player();
		} catch (IOException i) {
			return new Player();
		} catch (ClassNotFoundException c) {
			System.out.println("Player class not found");
			return new Player();
		}
		return player;
	}

	public static void main(String args[]){

		Scanner scanner = new Scanner(System.in);

		String playerSerializationPath = "res/save/player.ser";
		Player player = getPlayer(playerSerializationPath);

		System.out.println(player);

		UserManager.Master().setActivePlayer(player);
		SceneManager.Master().pushScene(new MainMenuScene());

		String command = "";

		while (SceneManager.Master().getActiveScene() != null){

			command = Kernel.readCommand();

			Kernel.Master().ProcessCommand(command);
		}

		System.out.println("game terminated");

		FileManager.SerializeObject(player , playerSerializationPath);
	}
}

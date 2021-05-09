package Manager.Kernel;

import Manager.SceneManager;
import Manager.UserManager;
import Utility.UtilityHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Kernel {

	private static Scanner kernelScanner;
	private static Kernel _master;
	private ArrayList<KernelCommand> availableCommands;
	private ArrayList<KernelCommand> directCommands;

	public static Kernel Master() {
		if (_master == null) {
			_master = new Kernel();
		}
		return _master;
	}

	public static String readCommand() {
		if (kernelScanner == null) {
			kernelScanner = new Scanner(System.in);
		}
		System.out.print(": ");
		return kernelScanner.nextLine();
	}

	public Kernel() {
		Initialize();
	}

	private void Initialize() {

		availableCommands = new ArrayList<KernelCommand>();

		directCommands = new ArrayList<KernelCommand>();
		directCommands.add(new KernelCommand("!!", new KernelRunnable() {
			@Override
			public void process(String[] params) {
				DisplayDirectCommands();
			}
		}));
		directCommands.add(new KernelCommand("!display", new KernelRunnable() {
			@Override
			public void process(String[] params) {
				DisplayAvailableCommands();
			}
		}));
		directCommands.add(new KernelCommand("!activeScene", new KernelRunnable() {
			@Override
			public void process(String[] params) {
				DisplayActiveScene();
			}
		}));
		directCommands.add(new KernelCommand("!player", new KernelRunnable() {
			@Override
			public void process(String[] params) {
				DisplayActivePlayer();
			}
		}));
		directCommands.add(new KernelCommand("!equipped", new KernelRunnable() {
			@Override
			public void process(String[] params) {
				DisplayEquippedInventory();
			}
		}));

	}

	public void SetAvailableCommands(ArrayList<KernelCommand> commands) {
		availableCommands = commands;
	}

	public void ProcessCommand(String commandCode) {

		String stripped = commandCode.strip();
		String parsed[] = stripped.split("\\s+");
		String commandBase = parsed[0];
		String[] params = null;
		if (parsed.length > 1) {
			params = Arrays.copyOfRange(parsed, 1, parsed.length);
		}


		if (UtilityHelper.isNumeric(commandBase)) {
			int commandIndex = Integer.parseInt(commandBase);
			if (commandIndex >= 1 && commandIndex <= availableCommands.size()) {
				availableCommands.get(commandIndex - 1).Process(params);
				return;
			}
		}

		for (KernelCommand command : availableCommands) {
			if (command.getCommandCode().equals(commandBase)) {
				command.Process(params);
				return;
			}
		}

		for (KernelCommand command : directCommands) {
			if (command.getCommandCode().equals(commandBase)) {
				command.Process(params);
				return;
			}
		}

		System.out.println("OOPS! There is no such command as " + commandBase);
		System.out.println("You can try using ! to access direct commands. In order to check available direct commands type '!!' without quotes");

	}

	public void DisplayEquippedInventory() {
		System.out.println(Arrays.toString(UserManager.Master().getActivePlayer().getInventory().getEquippedItems()));
	}

	public void DisplayAvailableCommands() {

		int commandIndex = 1;
		for (KernelCommand command : availableCommands) {
			System.out.print("(" + commandIndex + ") " + command.getCommandCode() + " ");
			for (String paramTitle : command.getParamTitles()){
				System.out.print("@param(" + paramTitle + ")");
			}
			System.out.println();
			commandIndex++;
		}
		System.out.println("There are " + Integer.toString(availableCommands.size()) + " commands available");
	}

	public void DisplayActiveScene() {

		String activeSceneClass = SceneManager.Master().getActiveScene().getClass().getName();
		System.out.println("You are currently at " + activeSceneClass);
	}

	public void DisplayDirectCommands() {
		for (KernelCommand command : directCommands) {
			System.out.println("* " + command.getCommandCode());
		}
	}

	public void DisplayActivePlayer() {
		System.out.println(UserManager.Master().getActivePlayer());
	}

	public static void ClearConsole() {
		System.out.println("clearing console");
		System.out.print("\033[H\033[2J");

		System.out.flush();
		/*
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}*/
	}
}
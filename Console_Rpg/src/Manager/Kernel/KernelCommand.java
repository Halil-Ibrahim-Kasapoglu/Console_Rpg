package Manager.Kernel;

public class KernelCommand{

	private String commandCode;
	private KernelRunnable runnable;

	public KernelCommand(String commandCode , KernelRunnable runnable){
		this.commandCode = commandCode;
		this.runnable = runnable;
	}

	public String getCommandCode(){
		return this.commandCode;
	}

	public void Process(){
		this.runnable.process();
	}

}
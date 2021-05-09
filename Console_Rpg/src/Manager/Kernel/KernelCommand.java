package Manager.Kernel;

public class KernelCommand{

	private String commandCode;
	private KernelRunnable runnable;
	// FIXME: 9.05.2021
	private String[] paramTitles;

	public String[] getParamTitles() {
		return paramTitles;
	}

	public KernelCommand(String commandCode , KernelRunnable runnable , String[] paramTitles){
		this.commandCode = commandCode;
		this.runnable = runnable;
		this.paramTitles = paramTitles;
	}

	public KernelCommand(String commandCode , KernelRunnable runnable){
		this.commandCode = commandCode;
		this.runnable = runnable;
		paramTitles = new String[]{};
	}

	public String getCommandCode(){
		return this.commandCode;
	}

	public void Process(String[] params){
		if (params == null){
			params = new String[]{};
		}

		for (String param : params){
			System.out.println("@param :"  + param );
		}

		if (params.length < paramTitles.length){
			System.out.println("be sure to write all params");
			System.out.print(commandCode + " ");
			for (String title : paramTitles){
				System.out.print(title + " ");
			}
			System.out.println();
			return;
		}else {
			this.runnable.process(params);
		}

	}

}
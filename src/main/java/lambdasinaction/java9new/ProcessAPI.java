package lambdasinaction.java9new;

public class ProcessAPI {
	public static void main(String[] args) {
		ProcessHandle currentProcess = 
				ProcessHandle.current();
		System.out.println(currentProcess.pid());
		System.out.println(currentProcess.info());
	}
}

package lambdasinaction._01lambda.basic1;

public class MeaningOfThis
{
	public final int value = 4;
	public void doIt()
	{
		int value = 6;
		Runnable r = new Runnable(){
			//public final int value = 5;
			public void run(){
				//int value = 10;
				System.out.println("Runnable 익명클래스를 따로 만들어서 Thread에 전달");
				System.out.println(value);
			}
		};
		Thread t1 = new Thread(r);
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread 인자로 전달된 Runnable 익명클래스");
				System.out.println(value);
			}
		});
		t2.start();

		Runnable r2 = () -> {
			System.out.println("Lambda 클래스를 따로 만들어서 Thread에 전달");
			System.out.println(this.value);
		};
		Thread t3 = new Thread(r2);
		t3.start();

		Thread t4 = new Thread(() -> {
			System.out.println("Thread의 인자로 전달된 Lambda 클래스");
			System.out.println(this.value);
		});
		t4.start();
	}
	public static void main(String...args)
	{       
		MeaningOfThis m = new MeaningOfThis();
		m.doIt(); // ???   
	}
}

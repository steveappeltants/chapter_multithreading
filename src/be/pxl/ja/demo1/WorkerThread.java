package be.pxl.ja.demo1;

public class WorkerThread extends Thread {

	@Override
	public void run() {
		System.out.println("Line A (" + Thread.currentThread().getName() + ")");
		System.out.println("Line B (" + Thread.currentThread().getName() + ")");
		System.out.println("Line C (" + Thread.currentThread().getName() + ")");
		System.out.println("Line D (" + Thread.currentThread().getName() + ")");
		System.out.println("Line E (" + Thread.currentThread().getName() + ")");
	}

	public static void main(String[] args) {
		WorkerThread workerThread = new WorkerThread();
		workerThread.start(); // !! once a Thread is start/terminated, you need to renew the object again.
		System.out.println("Line 1 (" + Thread.currentThread().getName() + ")");
		System.out.println("Line 2 (" + Thread.currentThread().getName() + ")");
		System.out.println("Line 3 (" + Thread.currentThread().getName() + ")");
		try {
			workerThread.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(workerThread.getState());
//		workerThread.start(); // !! once a Thread is start/terminated, you need to renew the object again.
	}
}
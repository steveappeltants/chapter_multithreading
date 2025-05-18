package be.pxl.ja.oefening1;

public class Main {
    public static void main(String[] args) {
        // Create and start 4 Talker threads
        Talker talker1 = new Talker(1);
        Talker talker2 = new Talker(2);
        Talker talker3 = new Talker(3);
        Talker talker4 = new Talker(4);
        
        // Solution with extents Threads
//        talker1.start();
//        talker2.start();
//        talker3.start();
//        talker4.start();
        
        // Create and start threads, starting Talker as a Runnable
        Thread thread1 = new Thread(talker1);
        Thread thread2 = new Thread(talker2);
        Thread thread3 = new Thread(talker3);
        Thread thread4 = new Thread(talker4);
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
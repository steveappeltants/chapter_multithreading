package be.pxl.ja.oefening8_1;

public class Talker extends Thread {
    public static void main(String[] args) {
        Talker talker1 = new Talker(1);
        Talker talker2 = new Talker(2);
        Talker talker3 = new Talker(3);
        Talker talker4 = new Talker(4);
        
        talker1.start();
        talker2.start();
        talker3.start();
        talker4.start();
        
    }
    private int id;
    
    public Talker(int id) {
        this.id = id;
    }
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Talker ID: " + id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
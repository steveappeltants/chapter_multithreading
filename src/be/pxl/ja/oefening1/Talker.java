package be.pxl.ja.oefening1;

// changed extends Thread into implements Runnable
public class Talker implements Runnable{
    
    private final int id;
    
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
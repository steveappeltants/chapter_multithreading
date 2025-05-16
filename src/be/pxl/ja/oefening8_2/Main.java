package be.pxl.ja.oefening8_2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 50000;
    private static final int NUMBER_OF_THREADS = 10;
    private static final boolean MULTY_THREADS = false;
    
    public static void main(String[] args) {
        System.out.println("Range [" + MIN_RANGE + "-" + MAX_RANGE + "]");
        
        long startTime = System.currentTimeMillis();
        
        if (!MULTY_THREADS) {
            DivisorCounter singleCounter = new DivisorCounter(MIN_RANGE, MAX_RANGE);
            singleCounter.findHighestNumberDivisors();
            
            System.out.println("Single-threads approach");
            System.out.println("-----------------------");
            System.out.println("Getal: " + singleCounter.getNumberWithHighestDivisors());
            System.out.println("Aantal delers: " + singleCounter.getHighestDivisorCount());
        } else {
            List<DivisorCounter> counters = getDivisorCounters();

            for (DivisorCounter counter : counters) {
                try {
                    counter.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int globalMaxDivisors = 0;
            int globalNumber = 0;
            for (DivisorCounter counter : counters) {
                if (counter.getHighestDivisorCount() > globalMaxDivisors) {
                    globalMaxDivisors = counter.getHighestDivisorCount();
                    globalNumber = counter.getNumberWithHighestDivisors();
                }
            }
            System.out.println("Multithreads approach");
            System.out.println("---------------------");
            System.out.println("Getal: " + globalNumber);
            System.out.println("Aantal delers: " + globalMaxDivisors);
        }
        calculateDuration(startTime);
    }
    
    private static void calculateDuration(long startTime) {
        long endTime = System.currentTimeMillis();
        double executionTime = (endTime - startTime);
        System.out.println("Tijd: " + String.format("%.0f", executionTime) + " milliseconden");
    }
    
    private static List<DivisorCounter> getDivisorCounters() {
        List<DivisorCounter> counters = new ArrayList<>();
        // Create and start threads
        int rangePerThread = MAX_RANGE / NUMBER_OF_THREADS;
        for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
            int minRange = i * rangePerThread;
            int maxRange = (i == NUMBER_OF_THREADS - 1) ? MAX_RANGE : i * rangePerThread;
            
            DivisorCounter counter = new DivisorCounter(minRange, maxRange);
            counters.add(counter);
            counter.start();
        }
        return counters;
    }
}
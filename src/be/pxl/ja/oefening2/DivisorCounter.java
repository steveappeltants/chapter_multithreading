package be.pxl.ja.oefening2;

public class DivisorCounter extends Thread {
    private final int minRange;
    private final int maxRange;
    private int numberWithHighestDivisors;
    private int highestDivisorCount;
    
    public DivisorCounter(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
    
    @Override
    public void run() {
        findHighestNumberDivisors();
    }
    
    public void findHighestNumberDivisors() {
        int maxDivisors = 0;
        int number = 0;
        
        for (int i = minRange; i <= maxRange; i++) {
            int divisorCount = countDivisors(i);
            if (divisorCount > maxDivisors) {
                maxDivisors = divisorCount;
                number = i;
            }
        }
        this.highestDivisorCount = maxDivisors;
        this.numberWithHighestDivisors = number;
    }
    
    private int countDivisors(int num) {
        int count = 0;
        // Stops checking at square root of number, as divisors
        // larger than square root are paired with smaller ones
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (i == num / i) {
                    count++; // i==sqrt of num -> i == num/i -> +1
                } else {
                    count += 2; // num=28 -> i=2 -> num/i=14 -> +2
                }
            }
        }
        return count;
    }
    
    public int getNumberWithHighestDivisors() {
        return numberWithHighestDivisors;
    }
    
    public int getHighestDivisorCount() {
        return highestDivisorCount;
    }
}
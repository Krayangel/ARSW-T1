package edu.eci.arsw.math;

public class PiDigitsThreads extends Thread {
    private int start;
    private int count;
    private int n;
    private byte[] digits;

    public PiDigitsThreads(int start, int count, int n) {
        this.start = start;
        this.count = count;
        this.n = n;
        this.digits = new byte[count];
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            if (i % PiDigits.getDigitsPerSum() == 0) {
                double sum = 4 * PiDigits.sum(1, start)
                        - 2 * PiDigits.sum(4, start)
                        - PiDigits.sum(5, start)
                        - PiDigits.sum(6, start);

                start += PiDigits.getDigitsPerSum();

                for (int j = 0; j < PiDigits.getDigitsPerSum() && i + j < count; j++) {
                    sum = 16 * (sum - Math.floor(sum));
                    digits[i + j] = (byte) sum;
                }
            }
        }
    }

    public byte[] getDigits() {
        return digits;
    }
}
import java.util.*;

public class RadixSort {
    private static final int RADIX = 10;
    public static int comparacoes = 0;
    public static int movimentos = 0;
    public static long duration;

    public static void sort(int[] array) {
        long startTime = System.currentTimeMillis();

        List<Integer> negatives = new ArrayList<>();
        List<Integer> nonNegatives = new ArrayList<>();

        for (int item : array) {
            comparacoes++;
            if (item < 0) {
                negatives.add(-item);
            } else {
                nonNegatives.add(item);
            }
            movimentos++;
        }

        int[] negativesArray = negatives.stream().mapToInt(i -> i).toArray();
        int[] nonNegativesArray = nonNegatives.stream().mapToInt(i -> i).toArray();

        radixSort(negativesArray);
        radixSort(nonNegativesArray);

        for (int i = 0; i < negativesArray.length / 2; i++) {
            comparacoes++;
            int temp = negativesArray[i];
            negativesArray[i] = -negativesArray[negativesArray.length - 1 - i];
            negativesArray[negativesArray.length - 1 - i] = -temp;
            movimentos += 3;
        }

        int index = 0;
        for (int value : negativesArray) {
            array[index++] = -value;
            movimentos++;
        }
        for (int value : nonNegativesArray) {
            array[index++] = value;
            movimentos++;
        }

        duration = System.currentTimeMillis() - startTime;
    }

    private static void radixSort(int[] array) {
        int max = Arrays.stream(array).max().orElse(0);
        comparacoes++;
        for (int exp = 1; max / exp > 0; exp *= RADIX) {
            countSort(array, exp);
        }
    }

    private static void countSort(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[RADIX];
        Arrays.fill(count, 0);

        for (int value : array) {
            count[(value / exp) % RADIX]++;
            movimentos++;
        }

        for (int i = 1; i < RADIX; i++) {
            count[i] += count[i - 1];
            movimentos++;
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % RADIX] - 1] = array[i];
            count[(array[i] / exp) % RADIX]--;
            movimentos += 2;
        }

        System.arraycopy(output, 0, array, 0, array.length);
        movimentos += array.length;
    }

    public static String formatDuration(long duration) {
        long milliseconds = duration % 1000;
        long seconds = (duration / 1000) % 60;
        long minutes = (duration / (1000 * 60)) % 60;
        long hours = (duration / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d:%04d", hours, minutes, seconds, milliseconds);
    }
}
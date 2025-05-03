public class QuickSort {
    public static int comparacoes = 0;
    public static int movimentos = 0;
    public static long duration;

    public static void quickSort(int[] arr, int inicio, int fim) {
        long startTime = System.currentTimeMillis();

        if (inicio < fim) {
            int p = particionar(arr, inicio, fim);
            quickSort(arr, inicio, p - 1);
            quickSort(arr, p + 1, fim);
        }

        duration += System.currentTimeMillis() - startTime;
    }

    private static int particionar(int[] arr, int inicio, int fim) {
        int pivo = arr[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            comparacoes++;
            if (arr[j] <= pivo) {
                i++;
                trocar(arr, i, j);
            }
        }
        trocar(arr, i + 1, fim);
        return i + 1;
    }

    private static void trocar(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            movimentos++;
        }
    }

    public static String formatDuration(long duration) {
        long milliseconds = duration % 1000;
        long seconds = (duration / 1000) % 60;
        long minutes = (duration / (1000 * 60)) % 60;
        long hours = (duration / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d:%04d", hours, minutes, seconds, milliseconds);
    }
}
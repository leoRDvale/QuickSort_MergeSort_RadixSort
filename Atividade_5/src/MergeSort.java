import javax.swing.*;

public class MergeSort {
    public static int comparacoes = 0;
    public static int movimentos = 0;
    public static long duration;

    public static void mergeSort(int[] arr, int esquerda, int direita) {
        long startTime = System.currentTimeMillis();

        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSort(arr, esquerda, meio);
            mergeSort(arr, meio + 1, direita);
            merge(arr, esquerda, meio, direita);
        }
        long endTime = System.currentTimeMillis();

        duration = System.currentTimeMillis() - startTime;
    }

    public static void merge(int[] arr, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[esquerda + i];
        for (int j = 0; j < n2; j++) R[j] = arr[meio + 1 + j];

        int i = 0, j = 0, k = esquerda;

        while (i < n1 && j < n2) {
            comparacoes++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            movimentos++;
        }

        while (i < n1) {
            arr[k++] = L[i++];
            movimentos++;
        }

        while (j < n2) {
            arr[k++] = R[j++];
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
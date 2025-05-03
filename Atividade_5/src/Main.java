import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LerArquivo lerArquivo = new LerArquivo();
        String caminhoArquivoResultado = "/Users/leonardvale/Downloads/resultado.txt";

        int opcao;
        try {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "**** Escolha o Algoritmo De Ordenação ****\n\n1 - Quick Sort\n2 - Merge Sort\n3 - Radix Sort\n4 - Sair"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor,selecione uma opção.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int [] arrayNumeros = lerArquivo.lerArrayDoArquivo("dados500_mil.txt");

        switch (opcao) {
            case 1:
                QuickSort.quickSort(arrayNumeros, 0, arrayNumeros.length - 1);
                JOptionPane.showMessageDialog(null, "**** Quick Sort ****\n\nTempo de execução: " + QuickSort.formatDuration(QuickSort.duration) +
                        "\nComparações: " + QuickSort.comparacoes + "\nQuantidade de trocas: " + QuickSort.movimentos, "Quick Sort", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                MergeSort.mergeSort(arrayNumeros, 0, arrayNumeros.length - 1);
                JOptionPane.showMessageDialog(null, "**** Merge Sort ****\n\nTempo de execução: " + MergeSort.formatDuration(MergeSort.duration) +
                        "\nComparações: " + MergeSort.comparacoes + "\nQuantidade de trocas: " + MergeSort.movimentos, "Merge Sort", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                int[] arr = lerArquivo.lerArrayDoArquivo("dados500_mil.txt");
                RadixSort.sort(arr);
                JOptionPane.showMessageDialog(null, "**** Radix Sort ****\n\nTempo de execução: " + RadixSort.formatDuration(RadixSort.duration) +
                        "\nComparações: " + RadixSort.comparacoes + "\nQuantidade de trocas: " + RadixSort.movimentos, "Radix Sort", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Escolha inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(caminhoArquivoResultado))) {
            bufferedWriter.write(Arrays.toString(arrayNumeros));
            JOptionPane.showMessageDialog(null, "Arquivo ordenado criado em: " + caminhoArquivoResultado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
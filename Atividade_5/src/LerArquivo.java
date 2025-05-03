import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivo {
    public int[] lerArrayDoArquivo(String nomeArquivo) {
        List<Integer> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.trim().split("\\s+");
                for (String valor : valores) {
                    if (!valor.isEmpty()) {
                        lista.add(Integer.parseInt(valor.replaceAll("[^0-9-]", "")));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int[] array = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }
        return array;
    }
}

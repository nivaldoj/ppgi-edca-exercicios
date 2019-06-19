import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Mochila {

    // Knapsack(W, wt, n, b)
    public static int mochila(int pesoMaximo, int numeroElementos, int[] pesos, int[] beneficios) {
        // Tabela com valores intermediários que serão
        // usados nos cálculos futuros
        int tabela[][] = new int[numeroElementos + 1][pesoMaximo + 1];

        // Preenche a tabela com os valores calculados previamente
        // Para cada elemento existente
        for (int i = 1; i <= numeroElementos; i++) {
            // Para cada peso desse próximo elemento
            for (int w = 1; w <= pesoMaximo; w++) {
                // O elemento pode fazer parte da solução?
                if (pesos[i - 1] <= w) {
                    // Obtemos o valor máximo entre adicionar esse elemento
                    // À solução ótima corrente, dentro da tabela de valores
                    if ((beneficios[i-1] + tabela[i-1][w - pesos[i-1]]) > tabela[i-1][w]) {
                        // Função max(gx, gx' + cx)
                        tabela[i][w] = beneficios[i-1] + tabela[i-1][w - pesos[i-1]];
                        System.out.printf("%d\n", pesos[i-1]);
                    }
                    // Mantemos o peso anterior para esse produto adicionado
                    else {
                        tabela[i][w] = tabela[i-1][w];
                    }
                }
                // O elemento não pode fazer parte da solução
                else {
                    // Mantemos o peso anterior (o peso de i é maior que a capacidade)
                    tabela[i][w] = tabela[i-1][w];
                }
            }
        }

        // Obtemos o valor máximo colocado na mochila
        // Indo até o último elemento e o peso máximo possível
        return tabela[numeroElementos][pesoMaximo];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("C:\\Users\\nivas\\Desktop\\ppgi-edca-exercicios\\mochila\\instancias\\mochila01.txt.txt"));

        // Leitura
        int n = reader.nextInt();       // número de elementos
        int m = reader.nextInt();       // capacidade máxima da mochila

        int[] w = new int[n];           // peso dos elementos
        int[] b = new int[n];           // benefício dos elementos

        for (int i = 0; i < n; i++) {
            w[i] = reader.nextInt();
            b[i] = reader.nextInt();
        }

        System.out.println(mochila(m, n, w, b));

        reader.close();
    }

}

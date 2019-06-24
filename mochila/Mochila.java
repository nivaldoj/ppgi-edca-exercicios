import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Mochila {

    // Knapsack(L, m, p, v)
    // L    capacidade máxima da mochila
    // m    número de itens
    // p    pesos dos itens
    // v    valor de utilidade
    public static void mochila(int L, int m, int[] p, int[] v) {
        // matriz com a solução das instâncias da mochila (dp)
        int G[][] = new int[m + 1][L + 1];

        // matriz com os elementos que foram adicionados à i-ésima mochila
        boolean C[][] = new boolean[m + 1][L + 1];

        for (int i = 1; i <= m; i++) {
            // Para cada produto e peso de mochila
            for (int j = 0; j <= L; j++) {
                // Esse produto pode ser adicionado à mochila?
                // Ao adicionar o produto, não excede o peso máximo?
                // É obtido o valor máximo entre adicionar esse elemento
                // à solução ótima corrente, dentro da tabela de valores
                if ( (p[i-1] <= j) && (v[i-1] + G[i-1][j - p[i-1]] > G[i-1][j]) ) {
                    // max(gx, gx' + cx)
                    // Adicionamos o item à mochila e marcamos como adicionado
                    G[i][j] = v[i-1] + G[i-1][j - p[i-1]];
                    C[i][j] = true;
                }
                // O elemento não faz parte da solução (não pode ser adicionado)
                else {
                    // Não adicionamos o item, mantendo o peso anterior
                    // para a última capacidade da mochila calculada
                    G[i][j] = G[i-1][j];
                    C[i][j] = false;
                }
            }
        }

        // Obtêm quais foram os produtos adicionados na solução
        TreeSet<Integer> P = new TreeSet<>();
        for (int i = m, j = L; i >= 1; i--) {
            // Esse item foi adicionado à mochila?
            if (C[i][j]) {
                // Adicionamos-os no conjunto
                P.add(i);

                // E vamos até a próxima posição
                // para ver se o item foi adicionado
                j = j - p[i-1];
            }
        }

        // Exibe quais foram os produtos adicionados na solução
        System.out.println("produtos escolhidos: " + P);

        // Exibe o valor máximo colocado na mochila
        // O valor máximo é o último elemento da matriz solução
        System.out.println("valor: " + G[m][L]);
    }

    public static void print(boolean[][] m, int l, int c) {
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= c; j++) {
                System.out.print(m[i][j] ? "1\t" : "0\t");
            }
            System.out.println();
        }
    }

    public static void print(int[][] m, int l, int c) {
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= c; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("./instancias/mochila01.txt.txt"));

        // Leitura
        int n = reader.nextInt();       // número de elementos
        int m = reader.nextInt();       // capacidade máxima da mochila

        int[] w = new int[n];           // peso dos elementos
        int[] b = new int[n];           // benefício dos elementos

        for (int i = 0; i < n; i++) {
            w[i] = reader.nextInt();
            b[i] = reader.nextInt();
        }

        // Processa
        long t1 = System.currentTimeMillis();
        mochila(m, n, w, b);
        long t2 = System.currentTimeMillis();

        // Saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");

        reader.close();
    }

}

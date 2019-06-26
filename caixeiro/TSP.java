import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TSP {

    // Representação do infinito (valor máximo de um inteiro)
    public static final int INFINITO = Integer.MAX_VALUE;

    // TSP (M, b, d, o, n)
    public static int caixeiro(int[][] memoization, int bitmask, int[][] distancias, int cidadeAtual, int numeroCidades) {
        // Caso base, quando todas as cidades foram visitadas
        if (visitouTodasCidades(bitmask, numeroCidades)) {
            // Retorna a distância da cidade atual até a origem,
            // para ser adicionado à distância total percorrida
            return distancias[cidadeAtual][0];
        }

        // Se a distância das cidades já visitadas até essa cidade) já foi resolvido anteriormente
        if (visitado(memoization, bitmask, cidadeAtual)) {
            // Então retorne a distância já calculada da tabela da DP
            return memoization[bitmask][cidadeAtual];
        }

        // Por enquanto, definimos a menor distância para esse caminho para INF
        // pois ainda não sabemos qual é a menor distância possível
        int menorDistanciaSalva = INFINITO;

        for (int cidade = 0; cidade < numeroCidades; cidade++) {
            // Verificamos SE essa cidade pertence a solução atual (ao bitmask)
            if (solucaoNaoContem(cidade, bitmask)) {
                // Obtêm a distância da cidade atual até essa cidade (u até v)
                int distanciaUV = distancias[cidadeAtual][cidade];

                // Inclui essa cidade na máscara de cidades visitadas
                int bmCidadeInclusa = incluirCidade(cidade, bitmask);

                // Calcula a distância com a nova cidade inclusa e com o custo dela
                int menorDistanciaAtual = distanciaUV + caixeiro(memoization, bmCidadeInclusa, distancias, cidade, numeroCidades);

                // Salva a menor distância entre a atual e a prévia
                menorDistanciaSalva = Math.min(menorDistanciaSalva, menorDistanciaAtual);
            }
        }

        // Salvamos na tabela da DP (memoization) a menor distância
        // encontrada para esse conjunto de cidades até a cidade atual
        memoization[bitmask][cidadeAtual] = menorDistanciaSalva;

        // Retorna o valor salvo na DP
        return memoization[bitmask][cidadeAtual];
    }

    public static int incluirCidade(int cidade, int bitMask) {
        return (bitMask | (1 << cidade));
    }

    public static boolean solucaoNaoContem(int cidade, int bitMask) {
        return (bitMask & (1 << cidade)) == 0;
    }

    public static boolean visitado(int[][] memoization, int bitMask, int cidadeAtual) {
        return memoization[bitMask][cidadeAtual] != -1;
    }

    public static boolean visitouTodasCidades(int bitMask, int numeroCidades) {
        return bitMask == (1 << numeroCidades) - 1;
    }

    public static String binario(int n) {
        return Integer.toBinaryString(n);
    }

    public static void exibeMatriz(int[][] m, int l, int c) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("./instancias/pcv04.txt"));

        // Dimensão da matriz
        int N = Integer.parseInt(reader.nextLine());

        // Leitura das distâncias
        int D[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                D[i][j] = reader.nextInt();
            }
        }

        // Inicialização da memo table para a DP (inicialmente vazia)
        int M[][] = new int[N * N][N];
        for (int i = 0; i < (N * N); i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = -1;
            }
        }

        // Processa
        long t1 = System.currentTimeMillis();
        int s = caixeiro(M, 1, D, 0, N);
        long t2 = System.currentTimeMillis();

        // Saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");
        //System.out.println(s);


        reader.close();
    }

}

import java.io.*;
import java.util.*;

public class Kruskal {

    // Edge (U, V, W)
    public static class Aresta {

        private int vertice1;
        private int vertice2;
        private int peso;

        Aresta(int u, int v, int w) {
            this.vertice1 = u;
            this.vertice2 = v;
            this.peso = w;
        }

        public int obterVertice1() {
            return this.vertice1;
        }

        public int obterVertice2() {
            return this.vertice2;
        }

        public int obterPeso() {
            return this.peso;
        }

    }

    // G(V)
    public static class Grafo {

        private int numeroVertices;
        private ArrayList<Aresta> arestas;

        Grafo(int V) {
            this.numeroVertices = V;
            this.arestas = new ArrayList<>();
        }

        public void adicionarAresta(int vertice1, int vertice2, int peso) {
            // Cria uma nova aresta
            Aresta novaAresta = new Aresta(vertice1, vertice2, peso);

            // Adiciona essa aresta à lista de arestas
            this.arestas.add(novaAresta);
        }

        public int obterNumeroVertices() {
            return this.numeroVertices;
        }

        public ArrayList<Aresta> obterListaArestas() {
            return this.arestas;
        }

    }

    // MST-Kruskal (G, w)
    public static int kruskal(Grafo G) {
        // Árvore geradora mínima feita pelo algoritmo
        ArrayList<Aresta> arvore = new ArrayList<>();

        // Ordena as arestas pelo menor peso
        Collections.sort(G.obterListaArestas(), new Comparator<Aresta>() {
            @Override
            public int compare(Aresta o1, Aresta o2) {
                return Integer.compare(o1.peso, o2.peso);
            }
        });

        // Cria os subconjuntos de vértices
        int[] subconjunto = new int[G.obterNumeroVertices()];
        Arrays.fill(subconjunto, -1);

        for (int i = 0; i < G.obterListaArestas().size(); i++) {
            // Obtemos o subconjunto (Find-Set) dos dois vértices dessa aresta
            int subconjuntoV1 = buscar(subconjunto, G.obterListaArestas().get(i).obterVertice1());
            int subconjuntoV2 = buscar(subconjunto, G.obterListaArestas().get(i).obterVertice2());

            // Se o vértice 1 e 2 são diferentes, então não fazem parte
            // da mesma floresta, então não faz um ciclo - inserimos na AGM (MST)
            if (subconjuntoV1 != subconjuntoV2) {
                arvore.add(G.obterListaArestas().get(i));
                unir(subconjunto, subconjuntoV1, subconjuntoV2);
            }
        }

        // Obtêm o custo total, da árvore geradora mínima
        int custoTotal = 0;

        for (int i = 0; i < arvore.size(); i++) {
            custoTotal += arvore.get(i).obterPeso();
        }

        return custoTotal;
    }

    // Find-Set(U)
    public static int buscar(int[] subconjunto, int vertice) {
        // Se ainda não faz parte de nenhum subconjunto, retorne o próprio vértice
        if (subconjunto[vertice] == -1) {
            return vertice;
        }
        // Se não, procure o subconjunto desse vértice
        else {
            return buscar(subconjunto, subconjunto[vertice]);
        }
    }

    // Union-Set(U, V)
    public static void unir(int[] subconjunto, int vertice1, int vertice2) {
        // Obtêm o subconjunto do primeiro vértice (V1)
        int subconjuntoVertice1 = buscar(subconjunto, vertice1);

        // Obtêm o subconjunto do segundo vértice (V2)
        int subconjuntoVertice2 = buscar(subconjunto, vertice2);

        // Realiza a união entre os dois subconjuntos
        subconjunto[subconjuntoVertice1] = subconjuntoVertice2;
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("./instancias/dij50.txt"));

        // Número de vértices
        int N = Integer.parseInt(reader.nextLine().trim());

        // Inicializa o grafo
        Grafo g = new Grafo(N);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j)
                    continue;

                int w = reader.nextInt();
                g.adicionarAresta(i, j, w);
            }
        }

        // Processa
        long t1 = System.currentTimeMillis();
        int s = kruskal(g);
        long t2 = System.currentTimeMillis();

        // Saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");
        //System.out.println(s);

        reader.close();
    }

}

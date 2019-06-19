import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    // Valor "simbólico" para infinito
    public static final int INFINITO = Integer.MAX_VALUE;

    // adj(u, w)
    public static class Adjacencia {

        int vertice;
        int custo;

        Adjacencia(int u, int w) {
            this.vertice = u;
            this.custo = w;
        }
    }

    // G(V)
    public static class Grafo {

        private int numeroVertices;
        private ArrayList<ArrayList<Adjacencia>> adjacencias;

        Grafo(int V) {
            // Inicializa o número de vértices do grafo
            this.numeroVertices = V;

            // Inicializa a lista de adjacências (inicialmente vazia)
            this.adjacencias = new ArrayList<>(this.numeroVertices);
            for (int i = 0; i < this.numeroVertices; i++) {
                this.adjacencias.add(i, new ArrayList<>());
            }
        }

        public void adicionarAresta(int vertice1, int vertice2, int peso) {
            // Cria uma nova aresta para a lista de adjacências e adiciona-a
            this.adjacencias.get(vertice1).add(new Adjacencia(vertice2, peso));
        }

        public int obterNumeroVertices() {
            return this.numeroVertices;
        }

        public ArrayList<ArrayList<Adjacencia>> obterListaAdjacencias() {
            return adjacencias;
        }

    }

    // Dijkstra (G, w, s)
    public static int dijkstra(Grafo G, int origem, int destino) {
        // Vetor de distâncias (para saber a distância da origem até o I vértice)
        int[] distancia = new int[G.obterNumeroVertices()];

        // Vetor de visitados (para não expandir/relaxar se já tiver expandido)
        boolean[] visitado = new boolean[G.obterNumeroVertices()];

        // Cria uma fila de prioridades {distância, vértice}
        // A heap será ordenada pelos custos (menor para maior)
        PriorityQueue<Adjacencia> fila = new PriorityQueue<>(new Comparator<Adjacencia>() {
            @Override
            public int compare(Adjacencia o1, Adjacencia o2) {
                return Integer.compare(o1.custo, o2.custo);
            }
        });

        // Inicializa o vetor de distâncias e vértices visitados
        for (int i = 0; i < G.obterNumeroVertices(); i++) {
            // Inicializamos como infinito a distância de origem até i
            distancia[i] = INFINITO;

            // Marcamos como não visitado ainda
            visitado[i] = false;
        }

        // Inicializamos a distância da origem para ela mesmo e adicionamos à fila
        distancia[origem] = 0;
        fila.add(new Adjacencia(origem, distancia[origem]));

        while (!fila.isEmpty()) {
            // Extrai a próxima "adjacência" do topo da fila
            Adjacencia a = fila.poll();

            // Obtemos o vértice dessa adjacência/par (distancia, vertice)
            int u = a.vertice;

            // O vértice não foi expandido ainda?
            if (!visitado[u]) {
                // Marcamos-o como visitado
                visitado[u] = true;

                // Percorremos os vértices do vertice1 (V)
                // que são adjacentes do vértice2 (U)
                ArrayList<Adjacencia> adjU = G.obterListaAdjacencias().get(u);
                for (int i = 0; i < adjU.size(); i++) {
                    // Obtêm o vértice adjacente e o custo da aresta
                    int v = adjU.get(i).vertice;
                    int c = adjU.get(i).custo;

                    // Realiza o relaxamento RELAX(U, V)
                    if (distancia[v] > (distancia[u] + c)) {
                        // Atualiza a distância de V
                        // e insere na fila de prioridades
                        distancia[v] = distancia[u] + c;
                        fila.add(new Adjacencia(v, distancia[v]));
                    }
                }

            }
        }

        // Retorna a distância mínima da origem ao destino
        return distancia[destino];
    }

    public static void main(String[] args) throws FileNotFoundException {
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
        int s = dijkstra(g, 0, N-1);
        long t2 = System.currentTimeMillis();

        // Saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");
        //System.out.println(s);

        reader.close();

    }

}

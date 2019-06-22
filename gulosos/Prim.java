import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Prim {

    // INFINITY
    public static final int INF = Integer.MAX_VALUE;

    // adj(w, u)
    public static class Adjacencia {

        int custo;
        int vertice;

        Adjacencia(int u, int w) {
            this.custo = w;
            this.vertice = u;
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

    public static int prim(Grafo G) {
        // Vetor de distâncias à frente
        int distancia[] = new int[G.obterNumeroVertices()];

        // Vetor de visitados, para guardar quais foram processados
        boolean visitados[] = new boolean[G.obterNumeroVertices()];

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
            // Definimos todos os vértices como infinito
            // E marcamos-os como não visitados ainda
            distancia[i] = INF;
            visitados[i] = false;
        }

        // Definimos a distância do primeiro e adicionamos-o à fila
        distancia[0] = 0;
        fila.add(new Adjacencia(0, distancia[0]));

        while (true) {
            // Próximo vértice
            int u = -1;

            while (!fila.isEmpty()) {
                // Seleciona o vértice mais próximo
                int p = fila.poll().vertice;

                // Ignora-o se ele já foi visitado
                if (visitados[p])
                    continue;

                // Podemos usar esse vértice se não foi processado
                u = p;
                break;
            }

            // Finaliza se não há mais vértices a serem visitados
            if (u == -1)
                break;

            // Marcamos-o como visitado
            visitados[u] = true;

            // Percorremos os vértices do vertice1 (V)
            // que são adjacentes do vértice2 (U)
            ArrayList<Adjacencia> adjU = G.obterListaAdjacencias().get(u);
            for (int i = 0; i < adjU.size(); i++) {
                // Obtêm o vértice adjacente e o custo da aresta
                int v = adjU.get(i).vertice;
                int c = adjU.get(i).custo;

                // Ignoramos se já foi visitado esse vértice
                if (visitados[v])
                    continue;

                // Comparamos a distância atual com a distância desse vértice
                if (distancia[v] > c) {
                    // Atualiza a distância de V e insere na fila
                    distancia[v] = c;
                    fila.add(new Adjacencia(v, distancia[v]));
                }
            }
        }

        // Obtêm o custo da árvore geradora mínima
        int custoArvore = 0;
        for (int i = 0; i < G.obterNumeroVertices(); i++)
            custoArvore += distancia[i];

        // Retorna o custo da árvore
        return custoArvore;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("./instancias/dij10.txt"));

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
        int s = prim(g);
        long t2 = System.currentTimeMillis();

        // Saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");
        //System.out.println(s);

        reader.close();

    }

}

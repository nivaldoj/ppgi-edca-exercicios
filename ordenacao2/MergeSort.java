import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {

    private static void merge(int[] A, int p, int q, int r) {
        int L[] = new int[(q - p + 1) + 1];
        int R[] = new int[(r - q) + 1];

        for (int i = 0; i < (q - p + 1); i++)
            L[i] = A[p + i];
        for (int j = 0; j < (r - q); j++)
            R[j] = A[q + j + 1];

        int INF = Integer.MAX_VALUE;
        L[L.length - 1] = INF;
        R[R.length - 1] = INF;

        for (int k = p, i = 0, j = 0; k <= r; k++) {
            if (L[i] < R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
    }

    private static void mergeSort(int[] A, int p, int r) {
        int q;

        if (p < r) {
            q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, (q+1), r);
            merge(A, p, q, r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new FileReader("./instancias-num/num.100000.3.in"));

        int N = Integer.parseInt(stdin.readLine());
        int A[] = new int[N];

        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(stdin.readLine());

        stdin.close();

        long ini = System.currentTimeMillis();
        mergeSort(A, 0, N-1);
        long fim = System.currentTimeMillis();

        long tempo = (fim - ini);
        System.out.println("\nTempo: " + tempo + " milisegundos");
    }

}
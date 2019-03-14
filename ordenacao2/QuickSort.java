import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {

    public static int partition(int A[], int inicio, int fim) {
        int pivo = A[inicio];
        int i = inicio - 1;
        int j = fim + 1;
        int tmp;

        while (true) {
            do {
                i = i + 1;
            } while (i < fim && A[i] <= pivo);

            do {
                j = j - 1;
            } while (j > inicio && A[j] > pivo);

            if (i >= j)
                break;

            tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }

        tmp = A[inicio];
        A[inicio] = A[j];
        A[j] = tmp;

        return i-1;
    }

    private static void quickSort(int A[], int l, int r) {
        int q;

        if (l < r) {
            q = partition(A, l, r);
            quickSort(A, l, q);
            quickSort(A, q+1, r);
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
        quickSort(A, 0, A.length-1);
        long fim = System.currentTimeMillis();

        long tempo = (fim - ini);
        System.out.println("\nTempo: " + tempo + " milisegundos");
    }

}

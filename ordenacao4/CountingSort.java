import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CountingSort {
    
    public static int[] countingSort(int A[], int n, int k) {
        // array para saída ordenada e o auxiliar 
        int B[] = new int[n];
        int C[] = new int[k+1];
        
        // contagem do i-elemento no array
        for (int j = 0; j < A.length; j++)
            C[A[j]] = C[A[j]] + 1;
        
        // número de elementos <= ao i-elemento
        for (int i = 1; i <= k; i++)
            C[i] = C[i] + C[i-1];
        
        // usa frequência de i para por elementos ordenados
        for (int j = (A.length - 1); j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }

        return B;
    }

    public static int max(int A[]) {
        int max = 0;

        for (int i = 0; i < A.length; i++)
            if (A[i] > max)
                max = A[i];
        
        return max;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("./instancias-num/counting.txt"));

        // tamanho
        int n = Integer.parseInt(reader.readLine());

        // valores
        int A[] = new int[n];
        for (int i = 0; i < n; i++)
            A[i] = Integer.parseInt(reader.readLine());

        // ordena
        long t1 = System.currentTimeMillis();
        int B[] = countingSort(A, n, max(A));
        long t2 = System.currentTimeMillis();

        // saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");
        //System.out.println(Arrays.toString(A));

        reader.close();
    }

}
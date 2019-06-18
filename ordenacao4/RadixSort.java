import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RadixSort {

    public static void countingSort(int A[], int n, int d) {
        // array para saída ordenada e o auxiliar 
        int B[] = new int[n];
        int C[] = new int[10];  // conta dígitos de 0 à 9
        
        // contagem do dígito d do i-elemento no array
        for (int j = 0; j < A.length; j++)
            C[ ((A[j] / d) % 10) ] = C[ ((A[j] / d) % 10) ] + 1;
        
        // número de elementos <= ao i-dígito
        for (int i = 1; i < 10; i++)
            C[i] = C[i] + C[i-1];
        
        // usa frequência de i para por elementos ordenados
        for (int j = (A.length - 1); j >= 0; j--) {
            B[C[ ((A[j] / d) % 10) ] - 1] = A[j];
            C[ ((A[j] / d) % 10) ] = C[ ((A[j] / d) % 10) ] - 1;
        }

        // faz a cópia do array de saída ordenada para o
        // array recebido, para a próxima chamada do RadixSort
        for (int i = 0; i < n; i++)
            A[i] = B[i];
    }

    public static void radixSort(int A[], int n) {
        // obtêm valor máximo do array
        int m = max(A);

        // começa do dígito menos até o mais significativo
        for (int d = 1; (m/d) > 0; d *= 10) {
            //System.out.println((m/d) % 10);
            countingSort(A, n, d);
        }
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
        radixSort(A, n);
        long t2 = System.currentTimeMillis();

        // saída
        System.out.println("Tempo: " + (t2 - t1) + " ms");
        //System.out.println(Arrays.toString(A));

        reader.close();
    }
    
}
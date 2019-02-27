import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SelectionSort {

    public static long[] selectionSort(long a[], int n) {
        for (int i = 0; i < n; i++) {
            int iMin = i;
            
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[iMin]) {
                    iMin = j;
                }
            }

            if (a[i] != a[iMin]) {
                long temp = a[i];
                a[i] = a[iMin];
                a[iMin] = temp;
            }
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        // ---

        String filepath = "./instancias-num/num.100000.1.in";
        BufferedReader reader = new BufferedReader(new FileReader(filepath));

        int n = Integer.parseInt(reader.readLine());

        long a[] = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(reader.readLine());
        }

        long s[] = selectionSort(a, n);

        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }

        reader.close();

        // ---
        long stop = System.currentTimeMillis();
        double time = (double)(stop - start) / 1000;
        System.out.printf("\nTempo: " + time + " segundos\n");
    }

}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InsertionSort {

    public static long[] insertionSort(long a[], int n) {
        for (int i = 0; i < n; i++) {
            long p = a[i];
            int j = i - 1;

            while ((j >= 0) && (a[j] > p)) {
                a[j + 1] = a[j];
                j--;
            }

            a[j+1] = p;
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        // ---

        String filepath = "./instancias-num/num.100000.4.in";
        BufferedReader reader = new BufferedReader(new FileReader(filepath));

        int n = Integer.parseInt(reader.readLine());

        long a[] = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(reader.readLine());
        }

        long s[] = insertionSort(a, n);

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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HeapSort {

    public static long A[];
    public static int heapSize;

    public static void heapSort(long A[]) {
        buildMaxHeap(A);

        for (int i = A.length - 1; i >= 1; --i) {
            swap(A, 0, i);
            heapSize--;
            maxHeapify(A, 0);
        }
    }

    public static void buildMaxHeap(long A[]) {
        heapSize = A.length;

        for (int i = (int)(A.length / 2); i >= 0; --i) {
            maxHeapify(A, i);
        }
    }

    public static void maxHeapify(long A[], int i) {
        int l = left(i);
        int r = right(i);

        int largest = 0;
        if ((l < heapSize) && (A[l] > A[i])) {
            largest = l;
        } else {
            largest = i;
        }
           
        if ((r < heapSize) && (A[r] > A[largest])) {
            largest = r;
        }

        if (largest != i) {
            swap(A, i, largest);
            maxHeapify(A, largest);
        }
    }

    public static void swap(long A[], int i, int j) {
        long temp;
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    public static int left(int i) {
        return (2 * i) + 1;
    }

    public static int right(int i) {
        return (2 * i) + 2;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("./instancias-num/num.100000.4.in"));
        
        // size
        int n = Integer.parseInt(reader.readLine());

        // values
        A = new long[n];
        for (int i = 0; i < n; i++)
            A[i] = Long.parseLong(reader.readLine());

        // sort
        long t1 = System.currentTimeMillis();
        heapSort(A);
        long t2 = System.currentTimeMillis();

        // output
        System.out.println("Time: " + (t2 - t1) + " ms");
        //System.out.println(Arrays.toString(A));

        reader.close();
    }
    
}
package app;

import java.io.*;
import java.util.*;

class MinHeapNode {

    int element;
    int i;
    int j;

    public MinHeapNode(int element, int i, int j)
    {
        this.element = element;
        this.i = i;
        this.j = j;
    }
}
     
class MinHeap
{
    MinHeapNode[] harr;
    int heap_size;

    public MinHeap(MinHeapNode a[], int size)
    {
        heap_size = size;
        harr = a;
        int i = (heap_size -1)/2;
        while (i >= 0)
        {
            MinHeapify(i);
            i--;
        }
    }

    public void MinHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && harr[l].element < harr[i].element)
            smallest = l;
        if (r < heap_size && harr[r].element < harr[smallest].element)
            smallest = r;
        if (smallest != i)
        {
            swap(harr, i, smallest);
            MinHeapify(smallest);
        }
    }

    int left(int i) { return (2*i + 1); }
    int right(int i) { return (2*i + 2); }

    MinHeapNode getMin()
    {
        if(heap_size <= 0) {
            return null;
        }
        return harr[0];
    }

    void replaceMin(MinHeapNode root) {
        harr[0] = root;
        MinHeapify(0);
    }

    void swap(MinHeapNode[] arr, int i, int j) {
        MinHeapNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int[] mergeKSortedArrays(int[][] arr, int k) {
        MinHeapNode[] hArr = new MinHeapNode[k];
        int resultSize = 0;

        for (int i = 0; i < arr.length; i++) {
            MinHeapNode node = new MinHeapNode(arr[i][0], i, 1);
            hArr[i] = node;
            resultSize += arr[i].length;
        }

        MinHeap mh = new MinHeap(hArr, k);
        int[] result = new int[resultSize];
    
        for (int i=0; i < resultSize; i++) {
            MinHeapNode root = mh.getMin();
            result[i] = root.element;

            if (root.j < arr[root.i].length)
                root.element = arr[root.i][root.j++];
            else
                root.element = Integer.MAX_VALUE;

            mh.replaceMin(root);
        }

        return result;
    }

    static int[] mergeArrays(int[][] arr) {
        /*
         * Write your code here.
         */
         mergeKSortedArrays(arr, arr.length);
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrRows = Integer.parseInt(scan.nextLine().trim());
        int arrColumns = Integer.parseInt(scan.nextLine().trim());

        int[][] arr = new int[arrRows][arrColumns];

        for (int arrRowItr = 0; arrRowItr < arrRows; arrRowItr++) {
            String[] arrRowItems = scan.nextLine().split(" ");

            for (int arrColumnItr = 0; arrColumnItr < arrColumns; arrColumnItr++) {
                int arrItem = Integer.parseInt(arrRowItems[arrColumnItr].trim());
                arr[arrRowItr][arrColumnItr] = arrItem;
            }
        }

        int[] res = mergeArrays(arr);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bw.write(String.valueOf(res[resItr]));

            if (resItr != res.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}

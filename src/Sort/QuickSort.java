package Sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort<Key extends Comparable<Key>>{
    private Key[] a;

    public QuickSort(Key[] input) {
        a = input;
    }
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j)<0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 4, 23, 12};
        var qs = new QuickSort<Integer>(array);
        sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}

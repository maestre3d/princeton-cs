package alonsoruiz.whitelist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sort<T> {
    public static <T> void insertionSort(T[] items, Comparator<? super T> comparator) {
        for (int i = 1; i < items.length; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(items[j - 1], items[j]) > 0)
                    exch(items, j - 1, j);
                else break;
            }
        }
    }

    private static <T> void exch(T[] items, int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public static <T> void mergeSort(T[] items, Comparator<? super T> comparator) {
        T[] aux = items.clone();
        mergeSort(aux, items, 0, items.length, comparator);
    }

    private static <T> void merge(T[] src, T[] dst, int lo, int mid, int hi, Comparator<? super T> c) {
        // Merge dst[lo, mid] with dst[mid, hi] into src [0, hi-lo]
        int i = lo, j = mid, N = hi - lo;
        for (int k = 0; k < N; k++) {
            if (i == mid) src[k] = dst[j++];
            else if (j == hi) src[k] = dst[i++];
            else if (c.compare(dst[j], dst[i]) < 0) src[k] = dst[j++];
            else dst[k] = src[i++];
        }

        // Copy back into dst[lo, hi]
        for (int k = 0; k < N; k++)
            dst[lo + k] = src[k];
    }

    private static <T> void mergeSort(T[] src, T[] dst, int lo, int hi, Comparator<? super T> c) {
        int N = hi - lo;
        if (N <= 1) return;
        int mid = lo + N / 2;
        mergeSort(src, dst, lo, mid, c);
        mergeSort(src, dst, mid, hi, c);
        merge(src, dst, lo, mid, hi, c);
    }

    private static <T> ArrayList<ArrayList<T>> partitionList(T[] items, int p) {
        ArrayList<ArrayList<T>> chunks;
        if (p <= 1) {
            chunks = new ArrayList<>(1);
            chunks.add((ArrayList<T>) Arrays.stream(items).toList());
            return chunks;
        }

        chunks = new ArrayList<>(p);
        int sliceIndex = (items.length - 1) / p, pvt = 0, currentPartition = 0;
        boolean isFirstChunkItem = true;

        for (var i : items) {
            if (isFirstChunkItem) {
                chunks.add(new ArrayList<>());
                isFirstChunkItem = false;
            }

            chunks.get(currentPartition).add(pvt, i);

            if (pvt == sliceIndex) {
                pvt = 0;
                currentPartition++;
                isFirstChunkItem = true;
                continue;
            }

            pvt++;
        }
        return chunks;
    }
}
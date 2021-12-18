package hw3;

import hw3.MinHeap;

public class MinHeapSort {
    public static <E extends Comparable> void heapSort(E[] list) {
// Create a hw3.week10.Heap of integers
        MinHeap<E> heap = new MinHeap<E>();
// Add elements to the heap
        for (int i = 0; i < list.length; i++)
            heap.add(list[i]);
// Remove the highest elements from the heap
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }

    public static void sort(int[] list) {
// Create a hw3.week10.Heap of integers
        Heap<Integer> heap = new Heap<Integer>();
// Add elements to the heap
        for (int i = 0; i < list.length; i++)
            heap.add(list[i]);
// Remove the highest elements from the heap
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }
}

package hw3;

import java.util.ArrayList;
import java.util.Comparator;

public class GenericHeapUsingComparator<E extends Comparator> implements Cloneable{
    private ArrayList<E> list = new ArrayList<E>();
    private Comparator<? super E> comparator;

    public GenericHeapUsingComparator(E[] objects, Comparator<? super E> comparator) {
        this.comparator = comparator;
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    public void add(E newObject) {
        list.add(newObject); // Append to the end of the heap
        int currentIndex = list.size() - 1; // The index of the last node
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
// Swap if the current object is greater than its parent
            if (comparator.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }else break;
            currentIndex = parentIndex;
        }
    }

    public E remove() {
        if (list.size() == 0) return null;
        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
// Find the maximum between two children
            if (leftChildIndex >= list.size())
                break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size())
                if (comparator.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) maxIndex = rightChildIndex;
// Swap if the current node is less than the maximum
            if (comparator.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }
            else
                break; // The tree is a heap
        }
        return removedObject;
    }

    public boolean equals(GenericHeapUsingComparator<E> heap2) {
        GenericHeapUsingComparator<E> heap3 = this.clone();
        GenericHeapUsingComparator<E> heap4 = heap2.clone();

        if (heap3.list.size() != heap4.list.size()) return false;

        for (int i = 0; i < heap3.list.size(); i++) {
            if (heap3.remove() != heap4.remove()) return false;
        }

        return true;
    }

    public GenericHeapUsingComparator<E> clone() {
        ArrayList<E> list1 = (ArrayList<E>)this.list.clone();
        return new GenericHeapUsingComparator<E>((E[])list1.toArray(), this.comparator);
    }
}

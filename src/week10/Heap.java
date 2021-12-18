package week10;

import java.util.ArrayList;

public class Heap<E extends Comparable> {
    ArrayList<E> list;

    public Heap() {
        list = new ArrayList<>();
    }

    public void add(E element) {
        if (list.size() == 0) list.add(element);
        else {
            list.add(element);
            int index = list.size() - 1;
            while (true) {
                int otherIndex = (index - 1) / 2;
                E comparingElement = list.get(otherIndex);
                if (comparingElement.compareTo(element) < 0) {
                    E temp = list.get(index);
                    list.set(index, list.get(otherIndex));
                    list.set(otherIndex, temp);
                    index = otherIndex;
                }else break;
            }
        }
    }

    /*public void delete(E element) {
        E setter = list.remove(list.size() - 1);
        list.set(0, setter);

        int index = 0;
        while (true) {
            int index1 = index * 2 + 1;
            int index2 = index * 2 + 1;

            if (index2 < list.size()) {
                E max = null;
                if (list.get(index1).compareTo(list.get(index2)) < 0) {
                    max = list.get(index2);
                }else if (list.get(index1).compareTo(list.get(index2)) > 0) max = list.get(index1);
            }else if (index1 < list.size()) {
                E max = list.get(index1);
                if (list.)
            }
        }
    }*/

    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();

        heap.add(3);
        heap.add(4);
        heap.add(2);
        System.out.println(heap);
        heap.add(7);
        heap.add(6);
        heap.add(5);

        System.out.println(heap);
    }
}


package week7;

import week7.MyLinkedList;

public class Tester {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.set(2,7);
        System.out.println(list);

    }

}

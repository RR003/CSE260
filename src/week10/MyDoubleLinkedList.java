package week10;

public class MyDoubleLinkedList<E> {
    Node<E> head;
    Node<E> tail;
    int size = 0;

    public MyDoubleLinkedList(Node<E> node) {
        head = node;
        tail = node;
        size--;
    }

    public MyDoubleLinkedList() {}

    public int getSize() {return size;}

    public void addFirst(E value) {

        Node node = new Node(value);
        if (size == 0) {
            head = node;
            tail = head;
        }else if (size == 1) {
            head = node;
            tail.prev = head;
            head.next = tail;
            head.prev = tail;
            tail.next = head;
        }
        else {
            head.prev = node;
            node.next = head;
            head = node;
            head.prev = tail;
            tail.next = head;
        }
        size++;
    }

    public void addLast(E value) {
        Node node = new Node(value);
        if (size == 0) {
            tail = node;
            head = tail;
        }else if (size == 1){
            tail = node;
            head.next = tail;
            tail.prev = head;
        }
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public void add(E value, int index) {
        Node<E> current= head;
        if (index == 0) addFirst(value);
        else if (index == size) addLast(value);
        else {
            int i = 1;
            while (i < index) {
                // System.out.println(i);
                current= current.next;
                i++;
            }

            Node<E> node = new Node(value);
            node.next = current.next;
            current.next.prev = node;
            current.next = node;
            node.prev = current;

            size++;
        }
    }

    public void deleteFirst() {

        if (size == 1) {
            head = null;
            tail = null;
        }else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void deleteLast() {
        if (size == 1) {
            tail = null;
            head = tail;
        }else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void delete(int index) {
        if (index == 0) deleteFirst();
        else if (index == size - 1) deleteLast();
        else {
            Node<E> current = head;
            int i = 0;
            while (i < index) {
                i++;
                current = current.next;
            }
            current.next.prev = current.prev;
            current.prev.next = current.next;
            size--;
        }
    }

    public String toString() {
        String string = "[";
        int i = 0;
        Node<E> current = head;
        while (current != null) {
            if (i != 0) string += ", ";
            string = string + current.value;
            current = current.next;
            i++;
        }
        string += "]";
        return string;
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<>();
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.deleteLast();
        System.out.println(list); // [0.1]
        list.addLast(3);
        list.addLast(4);
        System.out.println(list); // [0,1,3,4]
        list.add(7,3); // [0,1,3,7,4]
        list.add(8, 4);
        System.out.println(list);  // [0,1,3,7,8,4]

        list.deleteLast(); // [0,1,3,7,8]
        System.out.println(list);

        list.delete(2);
        System.out.println(list);

        list.delete(1);
        System.out.println(list);

    }

}

class Node<E> {
    E value;
    Node prev;
    Node next;

    public Node(E value) {
        this.value = value;
    }

    public String toString() {
        return "value = " + value + " ,next = " + next;
    }
}



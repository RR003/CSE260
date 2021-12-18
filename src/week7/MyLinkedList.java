package week7;

public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    int size = 0;

    public MyLinkedList() {}
    public MyLinkedList(E[] elements) {
        for (E e : elements) addLast(e);
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null) // the list was empty before
            tail = head;
    }

    public void addLast(E e) {
        if (tail == null) // empty list
            head = tail = new Node<>(e);
        else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }
        size++;
    }
    public void add(int index, E e) {
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) return null;
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) tail = null; // empty list
            return temp.current;
        }
    }

    public E removeLast() {
        if (size == 0) return null;
        else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.current;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++)
                current = current.next;
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.current;
        }
    }


    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.current;
        }
    }

    public boolean contains(E e) {
        Node<E> current = head;
        while (current != null) {
            if (current.current == e) return true;
            current = current.next;
        }
        return false;
    }

    public E get(int index) {
        Node<E> current = head;
        int i = 0;
        while (true) {
            if (i == index) return current.current;
            current = current.next;
            i++;
        }
    }

    public int indexOf(E e) {
        Node<E> current = head;
        int i = 0;
        while (current != null) {
            if (current.current == e) return i;
            current = current.next;
            i++;
        }
        return 0;
    }

    public int lastIndexOf(E e) {
        Node<E> current = head;
        int index = -1;
        int i = 0;
        while (current != null) {
            if (current.current == e) index = i;
            current = current.next;
            i++;
        }
        return index;
    }

    public E set(int index, E e) {
        remove(index);
        add(index, e);
        return e;
    }



    @Override
    public String toString() {
        String s = "{";

        Node<E> current = head;
        while (current != null) {
            s += current.current;
            s += ", ";
            current = current.next;
        }
        s += "}";
        return s;
    }
}

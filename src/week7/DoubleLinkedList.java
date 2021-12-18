package week7;

import java.util.ListIterator;

public class DoubleLinkedList<E> {
    private Node<E> head, tail;
    int size = 0;

    /** Create a default list */
    public DoubleLinkedList() {
    }

    /** Create a list from an array of objects */
    public DoubleLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(i,objects[i]);
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        newNode.previous = null;
        head = newNode; // head points to the new node
        size++; // Increase list size
        if (tail == null) // the new node is the only node in list
            tail = head;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = newNode; // The new node is the only node in list
            tail = newNode;
        } else {
            tail.next = newNode; // Link the new with the last node
            newNode.previous = tail;
            newNode.next = null;
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the head
     * element is 0
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.previous).previous = current;
            (current.next).next = temp;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) tail = null;
            else  head.previous = null;
            return temp.element;
        }
    }

    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            tail.next = null;
            Node<E> temp = tail;
            tail = tail.previous;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the element
     * that was removed from the list.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
    }

    /** Return true if this list contains the element o */
    public boolean contains(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }
        return false;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
        checkIndex(index);

        Node<E> current = head;
        for (int i = 0; i < index; i++)
            if (i == index) {
                return current.element;
            }
        current = current.next;
        return null;
    }

    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return i;
            current = current.next;
        }
        return -1;
    }

    public int lastIndexOf(Object e) {
        int lastIdx = -1;
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                lastIdx = i;
            current = current.next;
        }
        return lastIdx;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException
                    ("Index: " + index + ", size: " + size);
    }

    public E set(int index, E e) {
        checkIndex(index);
        Node<E> current = head;

        E old = null;
        for (int i = 0; i < index; i++) {
            if (i == index) {
                old = current.element;
                current.element = e;
                return old;
            }
            current = current.next;
        }
        return null;
    }

    public ListIterator<E> listIterator() {
        ListIterator<E> iterator = new ListIterator<E>() {
            Node<E> current = head;
            Node<E> temp = null;
            int index = 0;

            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public boolean hasPrevious() {
                return (current.previous != null);
            }

            @Override
            public E next() {
                if (!hasNext()) throw new UnsupportedOperationException();

                temp = current;
                current = current.next;
                index++;
                return temp.element;
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) throw new UnsupportedOperationException();

                temp = current;
                current = current.previous;
                index --;
                return temp.element;
            }

            @Override
            public int previousIndex() {
                return (index - 1);
            }

            @Override
            public void remove() {
                if (temp.next != null) temp.next.previous = temp.previous;
                if (temp.previous != null) temp.previous.next = temp.next;

                if (temp == current) {
                    if (hasNext()) next();
                    else if (hasPrevious()) previous();
                    temp = null;
                }
            }

            @Override
            public void set(E e) {
                current.element = e;
            }

            @Override
            public void add(E e) {
                Node<E> newNode = new Node(e);
                newNode.next = current;
                newNode.previous = current.previous;
            }

        };

        return iterator;
    }


    public ListIterator<E> listIterator(int index) {
        ListIterator<E> iterator = listIterator();
        for (int i = 0; i < index; i++)
            iterator.next();
        return iterator;
    }


    public int size() {
        return size;
    }
}





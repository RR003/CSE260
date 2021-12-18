package week7;

public class MyAbstractList<E> implements MyList<E> {
    protected int size = 0; // The size of the list
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];


    // new methods on top

    public boolean addAll(MyList otherList) {
        boolean changed = false;

        for (int i = 0; i < otherList.size() ;i++) {
            add((E) otherList.get(i));
            changed = true;
        }
        return changed;
    }

    public boolean removeAll(MyList otherList) {
        boolean changed = false;

        for (int i = 0; i < otherList.size(); i++) {
            int j = indexOf((E) otherList.get(i));
            if (j >= 0) {
                remove(j);
                changed = true;
            }
        }
        return changed;
    }

    public boolean retainAll(MyList otherList) {
        boolean changed = false;

        for (int i = 0; i < size(); i++) {
            if (!otherList.contains(this.get(i))) {
                this.remove(i);
                i--;
                changed = true;
            }
        }
        return changed;
    }


    /** Create a default list */
    protected MyAbstractList() {
    }
    /** Create a list from an array of objects */
    protected MyAbstractList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }
    /** Add a new element at the end of this list */
    public void add(E e) {
        add(size, e); // add an element at index size
    }
    /** Return true if this list contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }
    /** Return the number of elements in this list */
    public int size() {
        return size;
    }

    /** Remove the first occurrence of the element from this list.
     * Shift any subsequent elements to the left.
     * Return true if the element is removed. */
    public boolean remove(E e) {
        int i = indexOf(e);
        if (i >= 0) {
            remove(i);
            return true;
        } else
            return false;
    }

    @Override /** Add a new element at the specified index */
    public void add(int index, E e) {
        ensureCapacity();
// Move the elements to the right after the specified index
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
// Insert new element to data[index]
        data[index] = e;
// Increase size by 1
        size++;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[])(new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public void clear() {
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return true;
        return false;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException
                    ("Index: " + index + ", Size: " + size);
    }
    @Override /** Return the index of the first matching element
     * in this list. Return -1 if no match. */
    public int indexOf(E e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return i;
        return -1;
    }
    @Override /** Return the index of the last matching element
     * in this list. Return -1 if no match. */
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--)
            if (e.equals(data[i])) return i;
        return -1;
    }

    @Override /** Remove the element at the specified position
     * in this list. Shift any subsequent elements to the left.
     * Return the element that was removed from the list. */
    public E remove(int index) {
        checkIndex(index);
        E e = data[index];
// Shift data to the left
        for (int j = index; j < size - 1; j++)
            data[j] = data[j + 1];
        data[size - 1] = null; // This element is now null
// Decrement size
        size--;
        return e;
    }
    @Override /** Replace the element at the specified position
     * in this list with the specified element. */
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(size*5).append("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }
        return result.append("]").toString();
    }

    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator
            implements java.util.Iterator<E> {
        private int current = 0; // Current index
        @Override
        public boolean hasNext() {
            return (current < size);
        }
        @Override
        public E next() {
            return data[current++];
        }
        @Override
        public void remove() {
            remove();
        }
    }
}

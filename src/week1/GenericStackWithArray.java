package week1;

public class GenericStackWithArray<E> {
    public final static int INITAL_SIZE = 16;
    private E[] elements;
    private int size;

    public GenericStackWithArray() {
        this(INITAL_SIZE);
    }

    public GenericStackWithArray(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
    }

    public E push(E value) {
        if(size >= elements.length) {
            E[] temp = ((E[]) new Object[elements.length * 2]);
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        return elements[size++] = value;
    }

    public E pop() {
        return elements[--size];
    }

    public E peek() {
        return elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        GenericStackWithArray<String> stringStack = new GenericStackWithArray<>();
        stringStack.push("one");
        stringStack.push("two");
        stringStack.push("three");
        System.out.println(stringStack.pop());
        String s = stringStack.pop();
        System.out.println(stringStack.pop());


        GenericStackWithArray<Integer> integerStack = new GenericStackWithArray<>();
        integerStack.push(1);
        integerStack.push(new Integer(2));
        System.out.println(integerStack.pop());
        System.out.println(integerStack.pop());


    }
}



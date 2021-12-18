package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SortWords {
    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("/Users/rahul/CSE260/src/test.txt");
        Scanner in = new Scanner(f);

        PriorityQueue<String> queue  = new PriorityQueue<>();
        while (in.hasNext()) {
            queue.offer(in.next());
        }
        in.close();

        System.out.println("All words.txt from the given file, in alhpabetical order");
        while (queue.size() > 0) {
            System.out.print(queue.remove() + " ");
        }
    }
}
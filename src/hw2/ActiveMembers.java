package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ActiveMembers {
    public static ArrayList<String> find_active_members(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter(",");
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] array = line.split(",");

            int active = 0;
            for (int i = 1; i <= 6; i++) {
                if (array[i].equals("1")) active++;
            }

            map.put(array[0], active);
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            if ((int)entry.getValue() >= 3) result.add((String)entry.getKey());
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list = find_active_members("/Users/rahul/CSE260/src/members.csv");
        System.out.println(list);
    }
}

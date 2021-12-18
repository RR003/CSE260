package hw2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Autocorrection {
    public static String autocorrect(String msg, HashMap<String, ArrayList<String>> mappings) {
        String[] array = msg.split(" ");

        String result = "";

        for (int i = 0; i < array.length; i++) {
            Iterator iterator = mappings.entrySet().iterator();
            boolean autoCorrect = false;
            while (iterator.hasNext()) {
                Map.Entry key = (Map.Entry)iterator.next();
                System.out.println(key.getKey());

                ArrayList<String> list = (ArrayList<String>)key.getValue();

                for (String s : list) {
                    if (s.equals(array[i])) {
                        result += (key.getKey() + " ");
                        autoCorrect = true;
                        break;
                    }
                }
                if (autoCorrect) break;


            }
            if (!autoCorrect) result += array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("hallo");
        list1.add("heello");
        list1.add("helo");
        list1.add("hell");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("haw");
        list2.add("hwo");


        ArrayList<String> list3 = new ArrayList<>();
        list3.add("r");
        list3.add("aer");

        ArrayList<String> list4 = new ArrayList<>();
        list4.add("yuo");
        list4.add("ouy");
        list4.add("uoy");
        list4.add("u");

        map.put("hello", list1);
        map.put("how", list2);
        map.put("are", list3);
        map.put("you", list4);

        String message = "hallo haw r u";
        String string = autocorrect(message, map);
        System.out.println(string);
    }
}

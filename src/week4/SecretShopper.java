package week4;

import java.text.NumberFormat;
import java.util.HashMap;

public class SecretShopper {

    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public static double grocery_shopping(HashMap<String, Integer> shopping_list,HashMap<String, Integer> child_sneaking, HashMap<String, Double> prices) {
        double total = 0;

        System.out.println("Receipt");

        for (String s: shopping_list.keySet()) {
            if (prices.containsKey(s)) {
                total = total + prices.get(s) * shopping_list.get(s);
                System.out.println(s.toUpperCase() + " x" + shopping_list.get(s) + ": "
                        + formatter.format((prices.get(s)*shopping_list.get(s))));

            }
        }
        System.out.println("---------------))((");


        for (String s : child_sneaking.keySet()) {
            if (prices.containsKey(s)) {
                total = total + prices.get(s) * child_sneaking.get(s);
                System.out.println(s.toUpperCase() + " x" + child_sneaking.get(s) + ": "
                        + formatter.format((prices.get(s)*child_sneaking.get(s))));
            }
        }

        return total;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> shopping_list = new HashMap<>();
        shopping_list.put("granola bars", 3);
        shopping_list.put("cheerios", 4);
        shopping_list.put("apples", 5);
        shopping_list.put("bananas", 10);
        shopping_list.put("oatmeal", 1);

        HashMap<String, Integer> child_sneaking = new HashMap<>();
        child_sneaking.put("flip flops", 1);
        child_sneaking.put("skittles", 15);
        child_sneaking.put("captain crunch", 1);

        HashMap<String, Double> prices = new HashMap<>();
        prices.put("apples", 0.25);
        prices.put("bananas", 1.25);
        prices.put("oatmeal", 10.49);
        prices.put("granola bars", 9.68);
        prices.put("cheerios", 2.40);
        prices.put("flip flops", 14.99);
        prices.put("skittles", 1.49);
        prices.put("captain crunch", 3.50);
        prices.put("twinkies", 1.30);



        System.out.println("Total: " + formatter.format(grocery_shopping(shopping_list, child_sneaking, prices)));
    }
}

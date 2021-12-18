package week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderLunches {
    public static int order_lunches(HashMap<String, Integer> stock, List<String> orders) {
        int purchased = 0;
        for (String s : orders) {
            if (stock.containsKey(s) && stock.get(s) > 0) {
                purchased++;
                stock.replace(s, stock.get(s) - 1);
            }
        }

        return purchased;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> stock = new HashMap<>();
        stock.put("soda", 0);
        stock.put("burger", 3);
        stock.put("chips", 5);
        stock.put("pizza", 7);

        List<String> orders = new ArrayList<>();
        orders.add("soda");
        orders.add("sandwich");
        orders.add("burger");
        orders.add("pizza");
        orders.add("pizza");
        orders.add("burger");

        System.out.println(order_lunches(stock, orders));
        System.out.println(stock);

    }
}

package hw2;

import java.io.*;
import java.util.HashMap;

public class CoffeeShop {
    public static String is_valid_order(String fileName, String[] orderList) throws IOException {
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String string = "";

        HashMap<String, Double> map = new HashMap<>();

        while ((string = br.readLine()) != null) {
            String[] array = string.split(",");
            map.put(array[0], Double.parseDouble(array[1]));
        }

        double cost = 0;

        for (String order : orderList) {
            if (!map.containsKey(order)) return "Your order is not valid";
            cost += map.get(order);
        }
        cost = Math.round(cost * 100) / 100.0;

        return "your order costs $" + String.format("%.2f", cost);
    }

    public static void main(String[] args) throws IOException {
        String[] order = {"Iced Coffee", "Cake Pop", "Croissant"};
        String result = is_valid_order("/Users/rahul/CSE260/src/coffee.txt", order);
        System.out.println(result);
    }
}

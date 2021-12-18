package week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ExpenseGrouping {
    public static HashMap<String, Double> compute_category_percentage(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner in = new Scanner(f);

        HashMap<String, Double> percentages = new HashMap<>();

        HashMap<String, Double> costs = new HashMap<>();
        costs.put("B", 0.0);
        costs.put("C", 0.0);
        costs.put("D", 0.0);
        costs.put("G", 0.0);
        costs.put("T", 0.0);

        while (in.hasNextLine()) {
            String[] item = in.nextLine().split(".");
            double thisCost = Double.parseDouble(item[1]);
            String thisCategory = item[2];

            if (costs.containsKey(item[2])) {
                double newCost = thisCost + costs.get(thisCategory);
                double newTotal = thisCost + costs.get("T");
                costs.put(thisCategory, newCost);
                costs.put("T", newTotal);
            }
        }
        in.close();

        if (costs.get("T") == 0.0) {
            return costs;
        }

        double percent = (costs.get("B")/ costs.get("T")) * 100;
        percentages.put("B", percent);
        percent = (costs.get("C") / costs.get("T")) * 100;
        percentages.put("C", percent);
        percent = (costs.get("D") / costs.get("T")) * 100;
        percentages.put("D", percent);
        percent = (costs.get("G") / costs.get("T")) * 100;
        percentages.put("G", percent);

        return percentages;

    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(compute_category_percentage("/Users/rahul/CSE260/src/test.txt"));
    }
}

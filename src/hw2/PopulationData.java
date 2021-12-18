package hw2;

import java.util.ArrayList;
import java.util.HashMap;

public class PopulationData {
    public int sum_populations(String continent, int minGDP, HashMap<String, ArrayList<String>> countries, HashMap<String, Integer> gdp, HashMap<String, Integer> populations) {
        ArrayList<String> listOfCountries = countries.get(continent);
        int sum = 0;
        for (String country : listOfCountries) {
            if (gdp.get(country) >= minGDP) {
                sum += populations.get(country);
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}

package Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day05/Input.txt");
        Scanner scanner = new Scanner(file);
        List<String> rules = new ArrayList<>();
        List<String> orders = new ArrayList<>();
        boolean readRules = true;
        while (scanner.hasNextLine()) {
            while (readRules) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    readRules = false;
                    break;
                }
                rules.add(line);
            }

            String line = scanner.nextLine();
            orders.add(line);
        }


        List<String> invalidOrders = new ArrayList<>();
        for (String order : orders) {
            List<String> entries = new ArrayList<>(List.of(order.split(",")));
            for (String rule : rules) {
                int orderOne = entries.indexOf(rule.split("\\|")[0]);
                int orderTwo = entries.indexOf(rule.split("\\|")[1]);

                if (orderTwo > -1 && orderOne > orderTwo) {
                    invalidOrders.add(order);
                    break;
                }
            }
        }

        List<List<String>> fixedOrders = new ArrayList<>();
        for (String invalidOrder : invalidOrders) {
            List<String> entries = new ArrayList<>(List.of(invalidOrder.split(",")));
            boolean valid = false;
            while (!valid) {
                for (String rule : rules) {
                    int orderOne = entries.indexOf(rule.split("\\|")[0]);
                    int orderTwo = entries.indexOf(rule.split("\\|")[1]);
                    if (orderTwo > -1 && orderOne > orderTwo) {
                        String entry = entries.remove(orderOne);
                        entries.add(orderTwo, entry);
                    }
                }
                valid = true;
                for (String rule : rules) {
                    int orderOne = entries.indexOf(rule.split("\\|")[0]);
                    int orderTwo = entries.indexOf(rule.split("\\|")[1]);
                    if (orderTwo > -1 && orderOne > orderTwo) {
                        String entry = entries.remove(orderOne);
                        entries.add(orderTwo, entry);
                        valid = false;
                    }
                }
            }
            fixedOrders.add(entries);
        }

        int result = 0;
        for (List<String> fixedOrder : fixedOrders) {
            result += Integer.parseInt(fixedOrder.get(fixedOrder.size() / 2));
        }
        System.out.println(result);
    }
}

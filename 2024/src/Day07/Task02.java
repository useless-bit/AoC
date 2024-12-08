package Day07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) throws IOException {
        File file = new File("2024/src/Day07/Input.txt");
        Scanner scanner = new Scanner(file);
        long result = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            long target = Long.parseLong(line.substring(0, line.indexOf(":")));

            List<Long> numbers = new ArrayList<>();
            for (String s : line.substring(line.indexOf(":") + 2).split("\\s+")) {
                numbers.add(Long.parseLong(s));
            }

            List<String> combinations = generateCombinations(new char[]{'+', '*', '|'}, numbers.size() - 1);
            for (String combination : combinations) {
                List<String> pattern = new ArrayList<>(List.of(combination.split("")));

                if (target == getCalculation(numbers, pattern)) {
                    result += target;
                    break;
                }
            }
        }
        System.out.printf(String.valueOf(result));
    }

    private static long getCalculation(List<Long> numbers, List<String> pattern) {
        long calculation = numbers.getFirst();
        for (int y = 1; y < numbers.size(); y++) {
            String operation = pattern.get(y - 1);
            if (operation.equalsIgnoreCase("+")) {
                calculation += numbers.get(y);
            } else if (operation.equalsIgnoreCase("*")) {
                calculation *= numbers.get(y);
            } else if (operation.equalsIgnoreCase("|")) {
                String numberOne = String.valueOf(calculation);
                String numberTwo = String.valueOf(numbers.get(y));
                calculation = Long.parseLong(numberOne.concat(numberTwo));
            } else {
                throw new RuntimeException();
            }
        }
        return calculation;
    }

    public static List<String> generateCombinations(char[] chars, int length) {
        List<String> results = new ArrayList<>();
        StringBuilder currentCombination = new StringBuilder();
        generateCombinationsRecursive(chars, currentCombination, length, results);

        return results;
    }

    private static void generateCombinationsRecursive(char[] chars, StringBuilder currentCombination, int remainingLength, List<String> results) {
        if (remainingLength == 0) {
            results.add(currentCombination.toString());
            return;
        }

        for (char c : chars) {
            currentCombination.append(c);
            generateCombinationsRecursive(chars, currentCombination, remainingLength - 1, results);
            currentCombination.deleteCharAt(currentCombination.length() - 1); // Backtrack
        }
    }
}

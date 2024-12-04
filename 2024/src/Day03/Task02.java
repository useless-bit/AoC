package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day03/Input.txt");
        Scanner scanner = new Scanner(file);
        int result = 0;
        String line = "";
        while (scanner.hasNextLine()) {
            line = line.concat(scanner.nextLine());
        }

        boolean enabled = true;
        while (true) {
            if (enabled) {
                int startIndex = line.indexOf("mul(") + 4;

                String checkForDont = line.substring(0, startIndex);
                if (checkForDont.contains("don't()")) {
                    line = line.substring(line.indexOf("don't()"));
                    enabled = false;
                    continue;
                }

                line = line.substring(startIndex);
                int endIndex = line.indexOf(")");
                if (startIndex - 4 == -1 || endIndex == -1) {
                    break;
                }
                String extractedString = line.substring(0, endIndex);
                String[] extractedValues = extractedString.split(",");
                if (extractedValues.length != 2 || !extractedValues[0].chars().allMatch(Character::isDigit) || !extractedValues[1].chars().allMatch(Character::isDigit)) {
                    continue;
                }
                System.out.println(Arrays.toString(extractedValues));
                int numberOne = Integer.parseInt(extractedValues[0]);
                int numberTwo = Integer.parseInt(extractedValues[1]);
                result += (numberOne * numberTwo);
            } else {
                int startIndex = line.indexOf("do()") + 4;
                if (startIndex - 4 == -1) {
                    break;
                } else {
                    line = line.substring(startIndex);
                    enabled = true;
                }
            }
        }

        System.out.println(result);
    }
}

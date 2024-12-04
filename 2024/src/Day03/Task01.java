package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day03/Input.txt");
        Scanner scanner = new Scanner(file);
        int result = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            while (true) {
                int startIndex = line.indexOf("mul(") + 4;
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
                int numberOne = Integer.parseInt(extractedValues[0]);
                int numberTwo = Integer.parseInt(extractedValues[1]);
                result += (numberOne * numberTwo);
            }
        }
        System.out.println(result);
    }
}

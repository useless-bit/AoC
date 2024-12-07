package Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day06/Input.txt");
        Scanner scanner = new Scanner(file);
        List<List<String>> field = new ArrayList<>();
        List<List<String>> map = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            field.add(new ArrayList<>(List.of(line.split(""))));
            map.add(new ArrayList<>(List.of(line.split(""))));
        }

        int positionX = 0;
        int positionY = 0;
        Orientation orientation = null;
        for (int y = 0; y < field.size(); y++) {
            for (int x = 0; x < field.get(y).size(); x++) {
                if (field.get(y).get(x).equalsIgnoreCase("<")) {
                    orientation = Orientation.LEFT;
                    positionX = x;
                    positionY = y;
                } else if (field.get(y).get(x).equalsIgnoreCase(">")) {
                    orientation = Orientation.RIGHT;
                    positionX = x;
                    positionY = y;
                } else if (field.get(y).get(x).equalsIgnoreCase("^")) {
                    orientation = Orientation.UP;
                    positionX = x;
                    positionY = y;
                } else if (field.get(y).get(x).equalsIgnoreCase("v")) {
                    orientation = Orientation.DOWN;
                    positionX = x;
                    positionY = y;
                }
            }
        }

        map.get(positionY).set(positionX, "X");
        field.get(positionY).set(positionX, ".");

        boolean moving = true;
        while (moving) {
            switch (orientation) {
                case UP -> {
                    if (positionY == 0) {
                        moving = false;
                        break;
                    }
                    String nextPositionElement = field.get(positionY - 1).get(positionX);
                    if (nextPositionElement.equalsIgnoreCase(".")) {
                        positionY -= 1;
                        map.get(positionY).set(positionX, "X");
                    } else if (nextPositionElement.equalsIgnoreCase("#")) {
                        orientation = updateOrientation(orientation);
                    }
                }
                case RIGHT -> {
                    if (positionX == field.get(positionY).size() - 1) {
                        moving = false;
                        break;
                    }
                    String nextPositionElement = field.get(positionY).get(positionX + 1);
                    if (nextPositionElement.equalsIgnoreCase(".")) {
                        positionX += 1;
                        map.get(positionY).set(positionX, "X");
                    } else if (nextPositionElement.equalsIgnoreCase("#")) {
                        orientation = updateOrientation(orientation);
                    }
                }
                case DOWN -> {
                    if (positionY == field.size() - 1) {
                        moving = false;
                        break;
                    }
                    String nextPositionElement = field.get(positionY + 1).get(positionX);
                    if (nextPositionElement.equalsIgnoreCase(".")) {
                        positionY += 1;
                        map.get(positionY).set(positionX, "X");
                    } else if (nextPositionElement.equalsIgnoreCase("#")) {
                        orientation = updateOrientation(orientation);
                    }
                }
                case LEFT -> {
                    if (positionX == 0) {
                        moving = false;
                        break;
                    }
                    String nextPositionElement = field.get(positionY).get(positionX - 1);
                    if (nextPositionElement.equalsIgnoreCase(".")) {
                        positionX -= 1;
                        map.get(positionY).set(positionX, "X");
                    } else if (nextPositionElement.equalsIgnoreCase("#")) {
                        orientation = updateOrientation(orientation);
                    }
                }
                case null, default -> {
                    System.out.println("Orientation == null");
                    return;
                }
            }
        }

        int result = 0;
        for (List<String> entries : map) {
            result += Collections.frequency(entries, "X");
        }
        System.out.println(result);
    }

    static Orientation updateOrientation(Orientation orientation) {
        switch (orientation) {
            case UP -> {
                return Orientation.RIGHT;
            }
            case RIGHT -> {
                return Orientation.DOWN;
            }
            case DOWN -> {
                return Orientation.LEFT;
            }
            case LEFT -> {
                return Orientation.UP;
            }
            case null, default -> {
                return null;
            }
        }
    }
}

package Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day06/Input.txt");
        Scanner scanner = new Scanner(file);
        List<List<String>> field = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            field.add(new ArrayList<>(List.of(line.split(""))));
        }

        int originalPositionX = 0;
        int originalPositionY = 0;
        Orientation originalOrientation = null;
        for (int y = 0; y < field.size(); y++) {
            for (int x = 0; x < field.get(y).size(); x++) {
                if (field.get(y).get(x).equalsIgnoreCase("<")) {
                    originalOrientation = Orientation.LEFT;
                    originalPositionX = x;
                    originalPositionY = y;
                } else if (field.get(y).get(x).equalsIgnoreCase(">")) {
                    originalOrientation = Orientation.RIGHT;
                    originalPositionX = x;
                    originalPositionY = y;
                } else if (field.get(y).get(x).equalsIgnoreCase("^")) {
                    originalOrientation = Orientation.UP;
                    originalPositionX = x;
                    originalPositionY = y;
                } else if (field.get(y).get(x).equalsIgnoreCase("v")) {
                    originalOrientation = Orientation.DOWN;
                    originalPositionX = x;
                    originalPositionY = y;
                }
            }
        }

        field.get(originalPositionY).set(originalPositionX, ".");

        final int[] result = {0};

        final boolean[] moving = new boolean[1];
        Thread thread;
        for (int y = 0; y < field.size(); y++) {
            for (int x = 0; x < field.get(y).size(); x++) {
                if (field.get(y).get(x).equalsIgnoreCase("#") || (x == originalPositionX && y == originalPositionY)) {
                    continue;
                }
                field.get(y).set(x, "#");
                int positionX = originalPositionX;
                int positionY = originalPositionY;
                Orientation orientation = originalOrientation;

                thread = new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                    result[0]++;
                    moving[0] = false;
                });
                thread.start();

                moving[0] = true;
                while (moving[0]) {
                    switch (orientation) {
                        case UP -> {
                            if (positionY == 0) {
                                moving[0] = false;
                                break;
                            }
                            String nextPositionElement = field.get(positionY - 1).get(positionX);
                            if (nextPositionElement.equalsIgnoreCase(".")) {
                                positionY -= 1;
                            } else if (nextPositionElement.equalsIgnoreCase("#")) {
                                orientation = updateOrientation(orientation);
                            }
                        }
                        case RIGHT -> {
                            if (positionX == field.get(positionY).size() - 1) {
                                moving[0] = false;
                                break;
                            }
                            String nextPositionElement = field.get(positionY).get(positionX + 1);
                            if (nextPositionElement.equalsIgnoreCase(".")) {
                                positionX += 1;
                            } else if (nextPositionElement.equalsIgnoreCase("#")) {
                                orientation = updateOrientation(orientation);
                            }
                        }
                        case DOWN -> {
                            if (positionY == field.size() - 1) {
                                moving[0] = false;
                                break;
                            }
                            String nextPositionElement = field.get(positionY + 1).get(positionX);
                            if (nextPositionElement.equalsIgnoreCase(".")) {
                                positionY += 1;
                            } else if (nextPositionElement.equalsIgnoreCase("#")) {
                                orientation = updateOrientation(orientation);
                            }
                        }
                        case LEFT -> {
                            if (positionX == 0) {
                                moving[0] = false;
                                break;
                            }
                            String nextPositionElement = field.get(positionY).get(positionX - 1);
                            if (nextPositionElement.equalsIgnoreCase(".")) {
                                positionX -= 1;
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
                thread.interrupt();
                field.get(y).set(x, ".");
            }
        }

        System.out.println(result[0]);
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

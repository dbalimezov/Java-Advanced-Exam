package RetakeExam;

import java.util.Scanner;

public class FormulaOne {
    public static int pRow = -1, pCol = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int commandsCount = Integer.parseInt(scanner.nextLine());
        char[][] field = new char[n][n];
        for (int i = 0; i < n; i++) {
            String row = scanner.nextLine();
            field[i] = row.toCharArray();
            if (row.contains("P")) {
                pRow = i;
                pCol = row.indexOf("P");
            }
        }

        boolean isOnFinish = false;
        for (int i = 0; i < commandsCount; i++) {
            String command = scanner.nextLine();
            switch (command) {
                case "up":
                    isOnFinish = movePlayer(-1, 0, field);
                    break;
                case "down":
                    isOnFinish = movePlayer(1, 0, field);
                    break;
                case "left":
                    isOnFinish = movePlayer(0, -1, field);
                    break;
                case "right":
                    isOnFinish = movePlayer(0, 1, field);
                    break;
            }
            if (isOnFinish) {
                System.out.println("Well done, the player won first place!");
                printMatrix(field);
                return;
            }
        }
        System.out.println("Oh no, the player got lost on the track!");

        printMatrix(field);
    }

    private static void printMatrix(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean movePlayer(int rowAddition, int colAddition, char[][] field) {
        if (isInBounds(rowAddition, colAddition, field)) {
            char symbol = field[pRow + rowAddition][pCol + colAddition];
            switch (symbol) {
                case 'B':
                    field[pRow][pCol] = '.';
                    pRow += rowAddition;
                    pCol += colAddition;
                    field[pRow][pCol] = 'B';
                    movePlayer(rowAddition, colAddition, field);
                    field[pRow][pCol] = 'P';
                    break;
                case 'T':
                    break;
                case '.':
                    if (field[pRow][pCol] != 'B' && field[pRow][pCol] != 'T') {
                        field[pRow][pCol] = '.';
                    }
                    field[pRow + rowAddition][pCol + colAddition] = 'P';
                    pRow += rowAddition;
                    pCol += colAddition;
                    break;
                case 'F':
                    field[pRow][pCol] = '.';
                    field[pRow + rowAddition][pCol + colAddition] = 'P';
                    return true;
            }
        } else {
            if (rowAddition == -1) {
                movePlayer(field.length - 1, 0, field);
            } else if (rowAddition == 1) {
                movePlayer(-pRow, 0, field);
            } else if (colAddition == -1) {
                movePlayer(0, field.length - 1, field);
            } else if (colAddition == 1) {
                movePlayer(0, -pCol, field);
            }
        }

        return false;
    }

    private static boolean isInBounds(int rowAddition, int colAddition, char[][] field) {
        return pRow + rowAddition >= 0 && pRow + rowAddition < field.length
                && pCol + colAddition >= 0 && pCol + colAddition < field.length;
    }
}

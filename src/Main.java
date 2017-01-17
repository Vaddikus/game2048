import java.util.Random;
import java.util.Scanner;

/**
 * Created by vadym on 04.01.17.
 */
public class Main {


    public static void main(String[] args) {

        boolean b = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter size of your grid: ");
        int n = scanner.nextInt();

        int[][] temp = new int[n][n];
        Grid g = new Grid(temp);
        g.start();
        g.showGrid();


        while (g.isContinue()) {
            System.out.println("Please select direction(w/s/a/d): ");
            String s = scanner.next();
            switch (s) {
                case "w":
                    g.moveUp();
                    g.showGrid();
                    break;
                case "s":
                    g.moveDown();
                    g.showGrid();
                    break;
                case "d":
                    g.moveRight();
                    g.showGrid();
                    break;
                case "a":
                    g.moveLeft();
                    g.showGrid();
                    break;
                default:
                    System.out.println("Please, enter correct direction");


            }

        }


    }

}


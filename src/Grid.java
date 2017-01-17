import java.util.*;

/**
 * Created by vadym on 04.01.17.
 */
public class Grid {

    int[][] grid;

    public int score = 0;

    List<Integer> cells = new ArrayList<>();
    int t = 0;

    public Grid(int[][] list) {
        grid = list;
    }

    public void start() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                cells.add(t++);
            }
        }
        createNumber();
        createNumber();
    }

    public void showGrid() {
        String dot = ".";
        System.out.println("Score: " + score);
        for (int[] x : grid) {
            for (int y : x) {
                if (y == 0)
                    System.out.printf("%3s ", ".");
                else
                    System.out.printf("%3d ", y);
            }
            System.out.println();
        }
        System.out.println();
    }


    public int[][] createNumber() {

        Collections.shuffle(cells);
        for (Integer cell : cells) {
            int x = cell / grid.length;
            int y = cell % grid.length;
            if (grid[x][y] == 0) {
                grid[x][y] = 2;
                break;
            }
        }

        return grid;
    }


    public boolean isContinue() {
        int[][] copy = new int[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        Grid tempGrid = new Grid(copy);
        int counter = 0;
        tempGrid.moveDown();
        for (int i = 0; i < grid.length; i++) {
            if (Arrays.equals(grid[i], tempGrid.grid[i])) {
                counter++;
            }
        }
        tempGrid = new Grid(copy);
        tempGrid.moveUp();
        for (int i = 0; i < grid.length; i++) {
            if (Arrays.equals(grid[i], tempGrid.grid[i])) {
                counter++;
            }
        }
        tempGrid = new Grid(copy);
        tempGrid.moveLeft();
        for (int i = 0; i < grid.length; i++) {
            if (Arrays.equals(grid[i], tempGrid.grid[i])) {
                counter++;
            }
        }
        tempGrid = new Grid(copy);
        tempGrid.moveRight();
        for (int i = 0; i < grid.length; i++) {
            if (Arrays.equals(grid[i], tempGrid.grid[i])) {
                counter++;
            }
        }


        if (counter == grid.length * 4) {
            System.out.println("Game is over");
            return false;
        }

        return true;

    }


    public void moveUp() {

        boolean allow = false;

        for (int i = 0; i < grid.length; i++) {
            int[] list = new int[grid.length];
            for (int j = 0; j < grid.length; j++) {
                list[j] = grid[j][i];
            }
            int[] t = process(list);
            if (!Arrays.equals(t, list))
                allow = true;

            for (int z = 0; z < grid.length; z++) {
                grid[z][i] = t[z];
            }

        }
        if (allow)
            createNumber();

    }


    public void moveDown() {
        boolean allow = false;

        for (int i = 0; i < grid.length; i++) {
            int[] list = new int[grid.length];
            for (int j = 0; j < grid.length; j++) {
                list[j] = grid[j][i];
            }
            int[] t = reverse(process(reverse(list)));
            if (!Arrays.equals(t, list))
                allow = true;
            for (int z = 0; z < grid.length; z++) {
                grid[z][i] = t[z];
            }

        }
        if (allow)
            createNumber();
    }


    public void moveRight() {

        boolean allow = false;
        int[] temp;
        for (int i = 0; i < grid.length; i++) {
            temp = grid[i];
            grid[i] = reverse(process(reverse(grid[i])));
            if (!Arrays.equals(temp, grid[i]))
                allow = true;
        }
        if (allow)
            createNumber();
    }

    public void moveLeft() {
        boolean allow = false;
        int[] temp;
        for (int i = 0; i < grid.length; i++) {
            temp = grid[i];
            grid[i] = process(grid[i]);
            if (!Arrays.equals(temp, grid[i]))
                allow = true;
        }
        if (allow)
            createNumber();
    }


    public int[] process(int[] list) {
        int[] temp = new int[list.length];
        int j = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != 0)
                temp[j++] = list[i];
        }
        for (int index = 1; index < temp.length; index++) {
            if (temp[index - 1] == temp[index]) {
                temp[index - 1] *= 2;
                temp[index] = 0;
                score += temp[index - 1];
            }
        }
        for (int z = 1; z < temp.length; z++) {
            if (temp[z - 1] == 0) {
                temp[z - 1] = temp[z];
                temp[z] = 0;
            }
        }

        return temp;
    }


//
//    public int[] processEND(int[] list)
//    {
//        int[] temp = process(reverse(list));
//
//        return reverse(temp);
//
//    }


    public int[] reverse(int[] list) {
        int[] array = new int[list.length];
        System.arraycopy(list, 0, array, 0, list.length);
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }


}

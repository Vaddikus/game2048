import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static java.util.Collections.reverse;

/**
 * Created by vadym on 04.01.17.
 */
public class Grid {

    int[][]grid;

    public static int score =0;

    public Grid(int[][] list) {
        grid = list;
    }

    public void start(){
        createNumber();
        createNumber();
    }

    public void showGrid()
    {
        String dot = ".";
        System.out.println("Score: " + score);
        for(int[] x: grid) {
            for (int y: x) {
                if (y == 0)
                    System.out.printf("%3s ",".");
                else
                    System.out.printf("%3d ", y);
            }
            System.out.println();
        }
        System.out.println();
    }


    public int[][] createNumber()
    {


        Random random = new Random();
        int rand_i = random.nextInt(grid.length);
        int rand_j = random.nextInt(grid.length);
        if (grid[rand_i][rand_j] == 0)
            grid[rand_i][rand_j] = 2;
        else
            createNumber();

        return grid;
    }




    public void moveUp() {



        boolean allow = false;

        for (int i = 0; i < grid.length; i++) {
            int[] list = new int[grid.length];
            for (int j = 0; j < grid.length; j++) {
                list[j] = grid[j][i];
            }
            int[] t = processBEGINING(list);
            if (!Arrays.equals(t,list))
                allow = true;

            for  (int z = 0; z < grid.length; z++) {
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
            int[] t = processEND(list);
            if (!Arrays.equals(t,list))
                allow = true;
            for  (int z = 0; z < grid.length; z++) {
                grid[z][i] = t[z];
            }

        }
        if(allow)
            createNumber();
    }


    public void moveRight() {

        boolean allow = false;
        int[] temp;
        for (int i = 0; i < grid.length; i++) {
            temp = grid[i];
            grid[i] = processEND(grid[i]);
            if (!Arrays.equals(temp,grid[i]))
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
            grid[i] = processBEGINING(grid[i]);
            if (!Arrays.equals(temp,grid[i]))
                allow = true;
        }
        if (allow)
            createNumber();
    }





    public  int[] processBEGINING(int[] list) {
        int[] temp = new int[list.length];
        int j = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != 0)
                temp[j++] = list[i];
        }
        for (int index = 1; index < temp.length; index++) {
            if (temp[index - 1] == temp[index]) {
                temp[index-1] *= 2;
                temp[index] = 0;
                score += temp[index-1];
               }
        }
        for (int z = 1; z < temp.length; z++) {
            if (temp[z-1] == 0) {
                temp[z-1] = temp[z];
                temp[z] = 0;
            }
        }

        return temp;
    }



    public int[] processEND(int[] list)
    {
        int[] temp = processBEGINING(reverse(list));

        return reverse(temp);

    }
    
    
    
    
    public int[] reverse(int[] list)
    {
        int[] array = new int[list.length];
        System.arraycopy(list,0,array,0,list.length);
        for (int i = 0, j = array.length - 1; i < array.length/2; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }


//    public  int[] processEND(int[] list) {
//        int[] temp = new int[list.length];
//        int j = temp.length-1;
//        for (int i = list.length-1; i >= 0; i--) {
//            if (list[i] != 0)
//                temp[j--] = list[i];
//        }
//
//        for (int index = temp.length-1; index > 0; index--) {
//            if (temp[index] == temp[index - 1]) {
//                temp[index] *= 2;
//                temp[index - 1] = 0;
//
//            }
//        }
//
//        for (int z = temp.length-1; z > 0; z--) {
//            for (int i = z; i > 0; i--) {
//                if (temp[i] == 0) {
//                    int t = temp[i];
//                    temp[i] = temp[i-1];
//                    temp[i - 1] = t;
//                }
//            }
//        }
//        return temp;
//    }



}

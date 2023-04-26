package Blatt03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AufgabeB3A1{
    public static void main(String[] args) {

        int[] result = readStandardIn();
        System.out.println(Arrays.toString(result));

    }
    public static int[] readStandardIn() throws NumberFormatException {

        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        while (sc.hasNextLine()) {
            try {
                int integerValue = Integer.parseInt(sc.nextLine());
                ls.add(integerValue);
            } catch (NumberFormatException e) {
                System.err.println("Ungueltige Eingabe");
                sc.close();
            }
        }
        sc.close();

        int[] result = new int[ls.size()];
        for(int i=0;i<result.length;i++){
            result[i] = ls.get(i);
        }
        return result;

    }

    public int subsetSize;
    public AufgabeB3A1(int subsetSize) {

    }
    public int[][] createSubsets(int[] data) {
        return null;
    }
    public int[] removeDuplicates(int[] data) {
        return null;
    }
 }
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

class Quicksort {

    static int counter = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        while (sc.hasNextLine()) {
            ls.add(Integer.parseInt(sc.nextLine()));
        }
        sc.close();

        // Creating and copying an array as many as the number entered
        int[] result = new int[ls.size()];
        
        for (int i = 0 ; i < ls.size() ; i ++) {
            result[i] = ls.get(i);
        }

        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }

        Instant start = Instant.now();
        qsort(result);
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        System.out.println("One Pivot Time: " + time);

        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }

        double med = result[0] + result[result.length-1];

        System.out.println("Min : " + result[result.length-1] + ", Med : " + med/2 + ", Max : " + result[0]);
        System.out.println("Count : " + counter);
        
    }


    public static int partition(int[] data , int l , int r) {

        if (l >= r) {
            return -1;
        } 

        int pivot = data[l];
        int i = l;
        int j = l;
        int k = r;

        while(j <= k) {
            if (data[j] > pivot) {
                int save = data[i];
                data[i] = data[j];
                data[j] = save;
                i++;
                j++;
                //System.out.println(counter + "]]]" +Arrays.toString(data));
                counter ++;
            } else if (data[j] < pivot) {
                int save = data[j];
                data[j] = data[k];
                data[k] = save;
                k--;
                //System.out.println(counter + "]]]" +Arrays.toString(data));
                counter ++;
            } else {
                j++;
                //System.out.println(counter + "]]]" +Arrays.toString(data));
                counter ++;
            }
        }
        return k;
    }

    public static void qsort(int[] data , int l , int r) {

        int a = partition(data , l , r);
        if (a != -1) {
            qsort(data , l , a);
            qsort(data , a+1 , r);
        }
        
    }

    public static void qsort(int[] data) {

        qsort(data , 0 , data.length -1);
        
    }



}
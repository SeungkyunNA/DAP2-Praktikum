import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

class Quicksort {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        while (sc.hasNextLine()) {
            try {
                String number = sc.nextLine();
                /* Da auf MacOS durch den 'seq' Befehl erzeugte Zahlen ab einer 
                   bestimmten Größe im Exponentialformat dargestellt werden, ist es erforderlich, 
                   diese zuerst in einen 'double' zu konvertieren. */
                double doubleValue = Double.parseDouble(number);
                int integerValue = (int) doubleValue;
                
                ls.add(integerValue);
            } catch (NumberFormatException e) {
                System.err.println("Ungueltige Eingabe");
                sc.close();
                return;
            }
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

        // Sort Start with Time measurement
        Instant start = Instant.now();
        
        qSort(result);
        
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        
        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }
        assert(isSorted(result)); 
        System.out.println("Single Pivot Time: " + time);

        if(result.length > 0) {
            if (result.length % 2 == 1) {
                int med = result[result.length/2];
                System.out.println("Min : " + result[result.length-1] + ", Med : " + med + ", Max : " + result[0]);
            } else {
                double d = result[result.length/2 - 1] + result[result.length/2];
                System.out.println("Min : " + result[result.length-1] + ", Med : " + d/2 + ", Max : " + result[0]);
            }
            
        }
    }

    public static int partition(int[] data , int l , int r) {

        int pivot = data[l];
        int pivot_pos = l;
        int iterator = l + 1;
        int right_area = r;

        while(iterator <= right_area) {
            if (data[iterator] > pivot) {
                int save = data[pivot_pos];
                data[pivot_pos] = data[iterator];
                data[iterator] = save;
                pivot_pos++;
                iterator++;

            } else if (data[iterator] < pivot) {
                int save = data[iterator];
                data[iterator] = data[right_area];
                data[right_area] = save;
                right_area--;

            } else {
                iterator++;
            }
        }
        return pivot_pos;
    }

    public static void qSort(int[] data , int l , int r) {

        if (l < r) {
            int a = partition(data , l , r);
        
            qSort(data , l , a);
            qSort(data , a+1 , r);
        }

    }

    public static void qSort(int[] data) {

        qSort(data , 0 , data.length -1);
        
    }

    public static boolean isSorted(int[] data) {
        for (int i = 1 ; i < data.length ; i++) {
            if (data[i-1] < data[i]) {
                return false;
            }
        }   
        return true;
    }



}
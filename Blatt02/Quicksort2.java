import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

class Quicksort2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        while (sc.hasNextLine()) {
            ls.add(Integer.parseInt(sc.nextLine()));
        }
        System.out.println("Size = " + ls.size());
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
        System.out.println("Dual - Pivot Time: " + time);

        assert(isSorted(result));

        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }

        double med = result[0] + result[result.length-1];

        System.out.println("Min : " + result[result.length-1] + ", Med : " + med/2 + ", Max : " + result[0]);
        
    }

    public static int[] partition(int[] data , int l , int r) {

        // Abbruchkriterium
        if (l >= r) {
            int[] result = {-1 , -1};
            return result;
        } 

        // Pivot Austausch. Damit garantiert immer A[l] > A[r]
        if (data[l]-data[r] < 0) {
            int save = data[l];
            data[l] = data[r];
            data[r] = save;
            //System.out.println(Arrays.toString(data));
        }

        int pivotMax = data[l];
        int pivotMin = data[r];
   
        int i = l;
        int j = l;
        int k = r;

        while(j <= k) {
            if (data[j] > pivotMax) {
                // Zahlen größer als PivotMax verschieben sich nach links.
                int save = data[i];
                data[i] = data[j];
                data[j] = save;
                // Die ausgetauschte Zahl kehrt wieder an die Stelle von i + 1 zurück.
                save = data[i+1];
                data[i+1] = data[j];
                data[j] = save;

                i++;
                j++;
                //System.out.print(Arrays.toString(data));
                //System.out.println("   i:" + i + ", j:" + j + ", k:" + k + " PiMax:" + pivotMax + ", PiMin:" + pivotMin);
            } else if (data[j] < pivotMin) {
                int save = data[j];
                data[j] = data[k];
                data[k] = save;
                k--;
                //System.out.print(Arrays.toString(data));
                //System.out.println("   i:" + i + ", j:" + j + ", k:" + k + " PiMax:" + pivotMax + ", PiMin:" + pivotMin);
            } else {
                j++;
                //System.out.print(Arrays.toString(data));
                //System.out.println("   i:" + i + ", j:" + j + ", k:" + k + " PiMax:" + pivotMax + ", PiMin:" + pivotMin);
            }

      
        }
        int[] result = {i, k};
        return result;
    }

    public static void qsort(int[] data , int l , int r) {

        int[] a = partition(data , l , r);
        if (a[0] != -1) {
            //System.out.println("");
            qsort(data , l , a[0]);         // Rekursion der linken Partition
            qsort(data , a[0]+1 , a[1]);    // Rekursion der mitten Partition
            qsort(data , a[1]+1 , r);       // Rekursion der rechten Partition
        }
        
    }

    public static void qsort(int[] data) {
        //System.out.println("First Call");
        qsort(data , 0 , data.length -1);
        
    }

    public static boolean isSorted(int[] data) {
        for (int i = 0 ; i < data.length -1 ; i++) {
            if (data[i] < data[i+1]) {
                return false;
            }
        }   
        return true;
    }



}
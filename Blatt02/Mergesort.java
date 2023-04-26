import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

class Mergesort {
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

        Instant start = Instant.now();
        result = mSort(result);
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();

        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }
        assert(isSorted(result));
        System.out.println("Merge Sort Time: " + time);

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

    public static int[] merge(int[] data, int l, int r, int m) {

        /* Da die Merge letztendlich nur zwischen l und r durchgeführt wird, wird ein Array der Größe (r-l+1) erstellt. 
        Dieses wird als Zwischenspeicher verwendet. */
        int[] result = new int[r-l+1]; 
        
        int lp = l;     // A[l , m]
        int rp = m+1;   // A[m+1 , r]

        for(int i = 0 ; i < result.length ; i++) {
            if (lp == m+1 && rp != r+1) {       // Falls das linke Array bereits im Ergebnisarray verwendet wurde
                result[i] = data[rp++];     
            } else if (lp != m+1 && rp == r+1) { // Falls das rechte Array bereits im Ergebnisarray verwendet wurde
                result[i] = data[lp++];
            }
            else if(data[lp] < data[rp]){   // kleinste Element in LinkeArray ist kleiner als rechte.
                result[i] = data[rp++];
            } else {                        // kleinste Element in RechteArray ist kleiner als linke.
                result[i] = data[lp++];
            }
        }

        for (int i = l ; i < r+1 ; i ++) {  // in data wird gesamte result-Array uebergeschrieben.
            data[i] = result[i-l];
        }
        return data;
    }

    public static int[] mSort(int[] data, int l, int r) {

        if (l >= r) {           // Abbruchkriterium
            return data;
        }

        int m = (l + r) / 2;    // Mittelwert festlegen.
        mSort(data, l , m);
        mSort(data, m+1 , r);

        return merge(data, l , r, m);
    }

    public static int[] mSort(int[] data) {
        return mSort(data, 0 , data.length-1);
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

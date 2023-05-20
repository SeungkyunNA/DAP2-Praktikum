import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

/* 

TEST CASE #1 : (unsorted Input)

Before Sorting : [177253449, 984422294, 623402426, ... ,236007191, 1019102979] Size of Input : 200000

LSD took : 19ms | Assert : true
MSD took : 9542ms | Assert : true

Sort After LSD : [1073740774, 1073738598, 1073690974, ... ,2096, 956]
Sort After MSD : [1073740774, 1073738598, 1073690974, ... ,2096, 956]


TEST CASE #2 : (sorted Input)

Before Sorting : [956, 2096, 8724, ... ,1073738598, 1073740774] Size of Input : 200000

LSD took : 20ms | Assert : true
MSD took : 9535ms | Assert : true

Sort After LSD : [1073740774, 1073738598, 1073690974, ... ,2096, 956]
Sort After MSD : [1073740774, 1073738598, 1073690974, ... ,2096, 956]


Time complexity :

LSD-sort : Da wir genau 4 mal auf Input durchlaufen. 4(n + MAX - min) -> O(n + MAX - min)
MSD-sort : Worst case, wenn alle Elementen unterschiedliche B-Byte haben, Rekursion wird  

*/

public class AufgabeB6A1{
    public static void main(String[] args) {
        /* Eingabe einlesen mit Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...) */
        int[] input_lsd; 
        int[] input_msd;
        try {
            input_lsd = readInput();
        } catch (NumberFormatException e){ 
            System.err.println("ERROR: Input is not valid."); 
            return;
        }

        if(input_lsd.length == 0) {
            System.err.println("No Input");
            return;
        }

        /* only for test!! */
        //Arrays.sort(input_lsd);

        input_msd = Arrays.copyOf(input_lsd , input_lsd.length);

        System.out.println();
        System.out.print("Before Sorting : " + arrToStr(input_lsd));
        System.out.println(" Size of Input : " + input_lsd.length);
        System.out.println();
        AufgabeB6A1 lsd = new AufgabeB6A1(input_lsd);
        AufgabeB6A1 msd = new AufgabeB6A1(input_msd);

        // Sort Start with Time measurement
        Instant start = Instant.now();
        lsd.lsdRadix();
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();

        boolean lsd_sort = isSort(lsd.data);
        assert(lsd_sort);
        System.out.println("LSD took : " + time + "ms" + " | Assert : " + lsd_sort);


        start = Instant.now();
        msd.msdRadix();
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();

        boolean msd_sort = isSort(msd.data);
        assert(msd_sort);
        System.out.println("MSD took : " + time+ "ms" + " | Assert : " + msd_sort);

        System.out.println();
        System.out.println("Sort After LSD : " + arrToStr(lsd.data));
        System.out.println();
        System.out.println("Sort After MSD : " + arrToStr(msd.data));
        System.out.println();
        
    }

    /* Class Object */

    public int[] data;
    public int count = 0;
    public AufgabeB6A1(int[] data) {
        this.data = data;
    }
    
    public int[] sortByByte(int l, int r, int b) {
        count++;
        int[] arrTemp = new int[r - l + 1]; 
        

        /* Erstelle ein Array ( C ) fuer die Heufigkeit des Schluessels "count()" */
        /* Danach "countToIdx()" addieret je Schluessel miteinander, um position zu stimmen (C') */
        int[] c_freq = count(data,l,r,b);    
        int[] freq = countToIdx(c_freq);

        for (int j = r ; j >= l ; j --) {
            int q = (data[j] >> 8*b) & 0xFF; // ermittle den Schlüssel q von A[j]
            arrTemp[freq[q]-1] = data[j];    // speichere in Hilfsarray entsprechende Werte
            freq[q]--;                       // Position um 1 verringen
        }

        
        /* Erstelle ein Array um die Schlussel zu speichern und zurueckzugeben.*/
        int[] keyList = new int[data.length];
        for (int i = 0 ; i < keyList.length ; i++) {
            keyList[i] = -1;            // init = -1 
        }

        /* Sortierung wird in Hilfsarray fertig. kopieren wir zu data[] */
        /* in Keylist wird auch kopiert, aber nur die Keys  */
        for (int idx = l ; idx < r+1 ; idx ++) {
            data[idx] = arrTemp[idx-l];        
            keyList[idx] = (arrTemp[idx-l] >> 8*b) & 0xFF;
        }

        return keyList;

    }

    public void lsdRadix() {
        for(int i = 0 ; i < 4 ; i ++) {        // b = 0 ist niederwerttigte Byte
            sortByByte(0, data.length-1, i);    
        }
        System.out.println("LSD SortByByte Call : " + count);
    }
    
    /* Naive Funkction for MSD */
    public void insertSort(int l , int r) {

        int target;
        for (int i = l+1 ; i <=r ; i ++) {
            target = data[i];
            int j = i - 1;
            while (j >= l && data[j] < target) {
                int save = data[j];
                data[j] = data[j+1];
                data[j+1] = save;
                j--;
            }
        }
    }
    
    public void msdRadix(int l, int r, int b) {
        
        /* Loop-end condition */
        if(b < 0) {
            return;
        }

        if(r-l < 32) {
            insertSort(l,r);
            return;
        }

        /* Recive key of b-byte.  */
        int[] keys = sortByByte(l, r, b);

        /* Set the range where keys with the same value are stored. */
        int start = 0;
        int end = 1;

        while(end < data.length) {
            /* if is key = -1 , means not target-range of current Partition. Go to Else */
            /* if keys[start] keys[end] same value stored, increment end Pointer 1. */
            if(keys[start] != -1 && keys[start] == keys[end]){
                end++;

                /* If the end pointer has reached the end of the data, execute the function. */
                if(end == data.length) {
                    msdRadix(start , end-1 , b-1);
                }

            } else {

                /* The end pointer has finally found a different value from the start. */
                /* Now we can set our range and execute the function with a (b-1) byte sort. */
                if (keys[start] != -1 && start != end - 1) {
                    msdRadix(start , end-1 , b-1);
                }

                /* There is no matching value. Increment both start and end by 1. */
                start = end;
                end = start+1;
            }
        }
    }

    /* Initial call for MSD. */
    public void msdRadix() {
        msdRadix(0,  this.data.length-1, 3);
        System.out.println("MSD SortByByte Call : " + count);
    }


    /* StandardInput wird eingelesen und als Rückgabe ein int[] zurückgeben */
    public static int[] readInput() throws NumberFormatException {
    
        Scanner sc = new Scanner(System.in);
        
        int[] a = new int[1];
        int i = 0;
        while(sc.hasNextLine()) {

            /* Fehlerbehandlung unnoetig. Es wird in Main() abgefangen. */
            String s = sc.nextLine();
            double doubleValue = Double.parseDouble(s);
            int target = (int) doubleValue;
            
            if (i < a.length) {
                a[i] = target;
                i++;
            } else {
                /* Array a ist nicht genug fuer Eingabelaenge, erzeugen wir doppel Groesse Array a* */
                int[] aa = new int[a.length*2];
                for (int p=0;p<a.length;p++){
                    aa[p]=a[p];               
                }
                a = aa;
                a[i] = target;
                i++;
            }
        }

        /* schneiden wir genau bis Eingabelaenge ab, um keine NULLs dran enthalten zu koennen. */
        int[] result = new int[i];
        for(int n=0;n<result.length;n++){
            result[n]=a[n];
        }

        sc.close();
        return result;
    }

    /* Hilfsfunktion für Freq.Array */
    public int[] count(int[] d , int l , int r, int b) {

        int[] result = new int[256];

        for (int v = l ; v < r+1 ; v++){
            int shift = (data[v] >> 8*b) & 0xFF;
            result[shift]++;
        }
        return result;
    }

    /* Hilfsfunktion für Freq to Idxpostion marker */
    public int[] countToIdx(int[] freq) {

        int[] result = Arrays.copyOf(freq, freq.length);

        for (int i = result.length-1 ; i > 0 ; i--){
            result[i-1] = result[i-1] + result[i];
        }

        return result;
    }

    /* Assert für absteigene Sortierung */
    public static boolean isSort(int[] c) {

        for (int i = 0 ; i < c.length-1 ; i ++) {
            if (c[i] < c[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static String arrToStr(int[] p) {

        if (p.length > 10) {
            return "[" + p[0] +", " + 
            p[1]+", " + p[2]+"," + " ... ," + p[p.length-2] 
            + ", " + p[p.length-1] +  "]";
        } else {
            return Arrays.toString(p);
        }
        
    }


}
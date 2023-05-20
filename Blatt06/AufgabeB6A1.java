import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

/* 

TEST CASE #1 : (unsorted Input)

Before Sorting : [393933850, 896503597, 987087592, ... ,356210828, 311234094] Size of Input : 10000000

LSD took : 194ms | Assert : true (LSD SortByByte Call : 4 times)
MSD took : 353ms | Assert : true (MSD SortByByte Call : 16449 times)

Sort After LSD : [1073741818, 1073741616, 1073741609, ... ,45, 26]
Sort After MSD : [1073741818, 1073741616, 1073741609, ... ,45, 26]


TEST CASE #2 : (sorted Input)

Before Sorting : [26, 45, 120, ... ,1073741616, 1073741818] Size of Input : 10000000

LSD took : 190ms | Assert : true (LSD SortByByte Call : 4)
MSD took : 377ms | Assert : true (MSD SortByByte Call : 16449)

Sort After LSD : [1073741818, 1073741616, 1073741609, ... ,45, 26]
Sort After MSD : [1073741818, 1073741616, 1073741609, ... ,45, 26]


TEST CASE #3 : (Input size 1000 -> 10000000)

size 1000       : LSD : 0ms   ,  MSD 0ms
size 10000      : LSD : 3ms   ,  MSD 2ms
size 100000     : LSD : 11ms  ,  MSD 12ms
size 1000000    : LSD : 48ms  ,  MSD 58ms
size 10000000   : LSD : 191ms ,  MSD 370ms

Fazit : Es zeigt eine lineare Zeit unabhängig von der Sortierung des Eingangs. MSD ist etwas langsamer.


Time complexity :

LSD-sort : Da wir genau 4 mal auf Input durchlaufen. 4(n + MAX - min) -> O(n + MAX - min)
MSD-sort : Alle Elementen sind genau maximal 4 mal durch Countingsort gerechnet werden. d.h fuer MSD auch O(n + MAX - min).

Wieso MSD langsamer? : 
1.  Weil absolut mehrer Rekursion noetig als LSD 
2.  Insertsort kann zu hauefig angerufen werden (dann teilweise ist die Sortierung n^2 ausgelaufen)
3.  Viele IF-ELSE-Bloecke fuehren (unabhaengig von der tatsaechlichen Anzahl der Operationen) 
    zu einer Leistungseinbusse im Zusammenhang mit der "CPU branch prediction".
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


        input_msd = Arrays.copyOf(input_lsd , input_lsd.length);

        System.out.println();
        System.out.print("Before Sorting : " + arrToStr(input_lsd));
        System.out.println(" Size of Input : " + input_lsd.length);
        System.out.println();

        /* AUFGABE START (LSD) */

        AufgabeB6A1 lsd = new AufgabeB6A1(input_lsd);
        AufgabeB6A1 msd = new AufgabeB6A1(input_msd);

        Instant start = Instant.now(); // Sort Start with Time measurement
        lsd.lsdRadix();
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();

        boolean lsd_sort = isSort(lsd.data);
        assert(lsd_sort);
        System.out.println("LSD took : " + time + "ms" + " | Assert : " + lsd_sort);


        /* AUFGABE START (MSD) */

        start = Instant.now();         // Sort Start with Time measurement
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
    
    /* AUFGABE - Countingsort */
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


        /* Sortierung wird in Hilfsarray fertig. kopieren wir zu data[] */

        for (int idx = l ; idx < r+1 ; idx ++) {
            data[idx] = arrTemp[idx-l];        
        }

        /* Fuer MSD */
        return arrTemp;


    }

    /* AUFGABE - LSD Sort */
    public void lsdRadix() {
        for(int i = 0 ; i < 4 ; i ++) {        // b = 0 ist niederwerttigte Byte
            sortByByte(0, data.length-1, i);    
        }
        System.out.println("LSD SortByByte Call : " + count);
    }
    

    
    /* AUFGABE - MSD Sort */
    public void msdRadix(int l, int r, int b) {
        
        /* Loop-end condition */
        if(b < 0) {
            return;
        }

        /* Interval is too small */
        if(r-l < 32) {
            insertSort(l,r);
            return;
        }

        /* Recive key of b-byte.  */
        int[] keys = sortByByte(l, r, b);

        /* Set the range where keys with the same value are stored. */
        int start = 0;
        int end = 1;

        while(end < keys.length) {

            /* if keys[start] keys[end] same KEY of value stored, increment end Pointer 1. */
            if(((keys[start] >> 8*b) & 0xFF) == ((keys[end]>> 8*b) & 0xFF)){
                end++;

                /* If the end pointer has reached the end of the data, execute the function. */
                if(end == keys.length) {
                    msdRadix(l+start , l+end-1 , b-1);
                }

            } else {

                /* The end pointer has finally found a different value from the start. */
                /* Now we can set our range and execute the function with a (b-1) byte sort. */
                if (start != end - 1) {
                    msdRadix(l+start , l+end-1 , b-1);
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


    /*___________________Hilfsfunktion______________________ */


    /* StandardInput wird eingelesen und als Rueckgabe ein int[] zurueckgeben */
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

    /* Hilfsfunktion fuer Freq.Array */
    public int[] count(int[] d , int l , int r, int b) {

        int[] result = new int[256];

        for (int v = l ; v < r+1 ; v++){
            int shift = (data[v] >> 8*b) & 0xFF;
            result[shift]++;
        }
        return result;
    }

    /* Hilfsfunktion fuer Freq to Idxpostion marker */
    public int[] countToIdx(int[] freq) {

        int[] result = Arrays.copyOf(freq, freq.length);

        for (int i = result.length-1 ; i > 0 ; i--){
            result[i-1] = result[i-1] + result[i];
        }

        return result;
    }

    /* Assert fuer absteigene Sortierung */
    public static boolean isSort(int[] c) {

        for (int i = 0 ; i < c.length-1 ; i ++) {
            if (c[i] < c[i+1]) {
                return false;
            }
        }
        return true;
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

    /* for compact Array print */
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
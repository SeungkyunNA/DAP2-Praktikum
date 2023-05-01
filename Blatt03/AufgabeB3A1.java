import java.util.Arrays;
import java.util.Scanner;


public class AufgabeB3A1{
    public static void main(String[] args) {
        int[] result;
        try{
            result = readStandardIn();
        } catch (NumberFormatException e){  // Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...)
            System.err.println("ERROR: Input is not valid."); 
            return;
        }
        
        int k;
        if (args.length == 0) { // Fehlerbehandlung : wenn k nicht eingegeben wird.
            System.err.println("ERROR: The argument for subsetsize must be one natural number.");
            return;
        }

        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // Fehlerbehandlung : wenn k nicht eine Natuerliche Zahl ist. ("1,1" , "a")
            System.err.println("ERROR: The argument for subsetsize must be one natural number.");
            return;
        }

        if (k < 0) {    // Fehlerbehandlung : wenn k nicht eine negative Zahl ist.
            System.err.println("ERROR: The argument for subsetsize must be one natural number.");
            return;
        }

        if(k == 0) {    // Early Return : Es gibt immmer ein leere Array mit 1 Moeglichkeit wenn k = 0 ist.
            int [] err = new int [0];
            System.out.println(err);
            System.out.println("There are 1 subsets");
            return;
        }

        AufgabeB3A1 a = new AufgabeB3A1(k);
        int[] remove = a.removeDuplicates(result); // Duplikation abnehmen
        

        if(remove.length < k) { // Fehlerbehandlung : Falls n < k ist  
            System.err.println("ERROR: k is too large.");
            return;
        }

        int[][] subSets = a.createSubsets(remove);
        if(subSets == null) {   // Fehlerbehandlung : Wenn Eingabemenge nicht eingegeben wird.  
            System.err.println("ERROR: Set not entered.");
            return;
        }


        for(int[] c : subSets) {    // Ergebnis ausgeben
            System.out.println(Arrays.toString(c));
        }
        System.out.println("There are " + subSets.length + " subsets");

    }
    public static int[] readStandardIn() throws NumberFormatException {

        /* Prinzipiel ist aus uebung. (dynamische Eingabespeicherung mit statische Datenstructur.) */

        Scanner sc = new Scanner(System.in);
        
        int[] a = new int[1];
        int i = 0;
        while(sc.hasNextLine()) {
            
            int target = Integer.parseInt(sc.nextLine());   // Fehlerbehandlung unnoetig. Es wird in Main() abgefangen.
            if (i < a.length) {
                a[i] = target;
                i++;
            } else {
                int[] aa = new int[a.length*2];
                for (int p=0;p<a.length;p++){
                    aa[p]=a[p];                 //  Array a ist nicht genug fuer Eingabelaenge, erzeugen wir doppel Groesse Array a*
                }
                a = aa;
                a[i] = target;
                i++;
            }
        }

        int[] result = new int[i];
        for(int n=0;n<result.length;n++){           //  schneiden wir genau bis Eingabelaenge ab, um keine NULLs dran enthalten zu koennen.
            result[n]=a[n];
        }

        sc.close();
        return result;

    }

    public int subsetSize;

    public AufgabeB3A1(int subsetSize) {
        this.subsetSize = subsetSize;
    }

    public int[][] createSubsets(int[] data) {
        if(data == null || data.length == 0) {  // Fehlerbehandlung : Wenn Eingabemenge nicht eingegeben wird.
            return null;
        }

        /* Wir wissen schon die Laenge der Eingabe (n) und Groesse der Teilmenge (k). Daher koennen wir rechnen, wie gross die Ergebnis-Array sein muss */
        int amountSetUp = calFac(data.length);
        int amountSetDown = (calFac(data.length-subsetSize)*calFac(subsetSize)); // Anzahl der Teilmenge
        int amountSet;
        if (amountSetUp != 0 &&  amountSetDown != 0){   // Fehlerbehandlung : divide by zero vermeiden
            amountSet = amountSetUp / amountSetDown;
        } else {
            return null;
        }

        int[][] result = new int[amountSet][subsetSize]; // durch Binomialkoeffizientrechnung koennen wir vorher festlegen, wie gross es ist.
        
        int[] ptr = new int [subsetSize];

        for (int i=0;i<ptr.length;i++){ // Setze Pointers [k-1,k-2 ... 2,1,0]
            ptr[i] = subsetSize-i-1;
        }

        int n = 0;
        if(result.length != 0) {
            for (int idx = 0; idx < ptr.length ; idx++) { // Speichere in result[0].
                result[n][idx] = data[ptr[idx]];
            }
            n++;
        } else {
            return null;
        }

        while(n < result.length){
            
            if (ptr[0] == data.length-1){
                ptr = ptrReset(ptr, data.length);       // siehe unten (Hilfsfunktion : ptrReset)
            } else {
                ptr[0]++;
            }

            for (int idx = 0; idx < ptr.length ; idx++) { // Speichere in result[n].
                result[n][idx] = data[ptr[idx]];
            }
            
            n++;
        }
        return result;

    }
    
    public int[] removeDuplicates(int[] data) {
        boolean[] marker = new boolean[data.length]; // Position der Doppelte Zahl zu markieren. true := diese Position ist schon gelegt.
        int counter = data.length;                   // um zu zaehlen, wie viele Zahlen werden hingelegt. wenn irgendeine Position true setzt, verringert counter um 1.
        for(int i=0;i<data.length;i++){
            if(!marker[i]) {                         // Falls jetztige Position nicht true ist, beduetet data[i] ist noch nicht belegt. 
                int target = data[i];
                for(int j=i+1;j<data.length;j++){    // Suche die gleiche Zahl mit data[i](target). wenn wir finden, markieren wir true.
                    if(data[j]==target){
                        marker[j]=true;              // Gefunden, setzt dort true und counter --
                        counter--;
                    }
                }
            }
        }
        int[] result = new int[counter];            // insgesamt haben wir 'counter' Zahlen, die zurueckgegeben werden muessen.
        int it1 = 0;      // Iterator fuer result[]
        int it2 = 0;      // Iterator fuer data[] und marker[].
        while(it1<counter){
            if(marker[it2] == false){
                result[it1] = data[it2];
                it1++;
                it2++;
            } else {    // Falls marker[it2] == true ist, fuegen wir nicht hin und einfach it2 um 1 erhoehen.
                it2++;
            }
        }

        return result;
    }

    /* Hilfsfunktion. gibt n! zurueck */
    public int calFac(int n) {
        if(n == 0) {
            return 1;
        }
        int fac = n;
        for(int i = n-1 ; i >= 1 ; i--) {
            fac *= i;
        }
        return fac;

    }

    /* Hilfsfunktion. Pointers werden in richtigen Form zuruecksetzen.
    
    wenn das 0te Pointer grosser als die Laenge der Data wird, setzet die Pointers in richtige Form. 
    
    Bsp1) Mit data.length = 5 , [5,2,1] -> [4,3,1] 
    Bsp2) Mit data.length = 5 , [5,4,1] -> [4,3,2] 
    */

    private int[] ptrReset(int[] ptr, int d) {
        int size = d-1;

        for (int i = 0 ; i < subsetSize ; i ++) {
            if (ptr[i] == size) {
                size --;
            } else {
                ptr[i]++;
                for (int p = i ; p >= 0 ; p--) {
                    ptr[p] = ptr[i]+(i-p);
                }
                break;
            }

            if(size == -1) {
                return null;
            }
        }
        return ptr;
    }
    
 }
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB4A1 {

    public static void main(String[] args) {

        /* Eingabe einlesen mit Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...) */
        int[] input; 
        try {
            input = readStandardIn();
        } catch (NumberFormatException e){ 
            System.err.println("ERROR: Input is not valid."); 
            return;
        }
        
        /* Argument einlesen mit Fehlerbehandlung : Argument soll ein natürlichen Zahl sein. */
        if (args.length != 1) {
            System.err.println("ERROR: The Argument must be ONE natural number.");
            return;
        }

        /* Argument überprüfen : Argument soll ein natürlichen Zahl sein. */
        int k;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){ 
            System.err.println("ERROR: The Argument must be ONE natural number."); 
            return;
        }

        /* Argument überprüfen : Argument soll größer als 0 und kleiner als input.length sein */
        if (k > input.length || k < 1) {
            System.err.println("ERROR: Not enough input values."); 
            return;
        }
    
        /* BuildMaxHeap */
        MaxHeap heap = new MaxHeap(input.length);
        heap.add(input);
        int[] result = heap.getValues();

        System.out.println(Arrays.toString(result));

        /* Ausgabe */
        //System.out.println(heapSelect(result, k));
        //System.out.println(heapSelectFast(result, k));

    }
    public static int heapSelect(int[] arr, int k) {

        return 0;

    }
    public static int heapSelectFast(int[] arr, int k) {

        return 0;

    }

    /* StandardInput wird eingelesen und als Rückgabe ein int[] zurückgeben */
    public static int[] readStandardIn() throws NumberFormatException {

        /* Prinzipiel ist aus uebung. (dynamische Eingabespeicherung mit statische Datenstructur.) */
  
        Scanner sc = new Scanner(System.in);
        
        int[] a = new int[1];
        int i = 0;
        while(sc.hasNextLine()) {
  
           String s = sc.nextLine();
           
           double doubleValue = Double.parseDouble(s);
           int target = (int) doubleValue;  // Fehlerbehandlung unnoetig. Es wird in Main() abgefangen.
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
}



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
        heap.buildMax(input);
        
        /* Assert Test for isHeap , add , Extract , Peek, Extact and Add */

        assert(isHeap(heap.getValues()));

        int sizeBeforePeek = heap.getSize();
        int peekMax = heap.peekMax();
        assert(isPeek(heap.getValues() , peekMax , sizeBeforePeek));

        int[] backup = Arrays.copyOf(heap.getValues(), heap.getCapacity());
        int exMax = heap.extractMax();
        assert(isExtracted(backup,heap.getValues(),exMax));

        int sizeBeforeAdd = heap.getSize();
        heap.add(Integer.MAX_VALUE);
        assert(isAdded(heap.getValues() , sizeBeforeAdd));

        
        /* Ausgabe */
        System.out.println(heapSelect(input, k));
        System.out.println(heapSelectFast(input, k));

    }

    public static int heapSelect (int[] arr, int k) {

        MaxHeap heap = new MaxHeap(arr.length);
        heap.buildMax(arr);
        int result = 0;
        for (int i = 0 ; i < arr.length+1-k ; i++) {
            result = heap.extractMax();
            heap.maxHeapify(0);
        }
        return result;

    }
    public static int heapSelectFast (int[] arr, int k) {
        int count = k;
        int result = -1;
        int [] sizeK = new int[k];
        for (int i = 0; i<sizeK.length;i++) {
            sizeK[i] = arr[i];
        }
        MaxHeap heap = new MaxHeap(sizeK.length);
        heap.buildMax(sizeK);
        while (count<arr.length) {
            if ( heap.heap[0] > arr[count]) {
                heap.heap[0] = arr[count];
                heap.maxHeapify(0);
            }

            count++;
        }
        result = heap.heap[0];
        return result;
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

     /* Assert for Heap. */
     private static boolean isHeap(int[] data) {

        for (int i = 0 ; i < data.length ; i ++) {
            int current = i;
            int left = (current*2) + 1;
            int right = (current*2) + 2;

            if (left == data.length) {
                return true;
            } else if (right == data.length) {
                return data[current] >= data[left];
            } else {
                if (data[current] < data[left] || data[current] < data[right]) {
                    return false;
                }
            }
        }
        return true;
     }
      /* Assert for peekMax() .*/
    private static boolean isPeek(int[] data , int p , int sizeBeforePeek) {

        if (data.length != sizeBeforePeek) {    // Durch peek() darf nicht Size von Heap aendern.
            return false;
        }

        if (data[0] != p) {   // p sollte am grossten Zahl in backup[] sein, und befindet sich genau 0 te index
            return false;
        }

        return true;
     }

     /* Assert for Extract.
      * backup : Array bevor Extraction
      * data   : Array nachdem Extraction
      * e      : Die aus dem Backup extrahierte Zahl
     */
     private static boolean isExtracted(int[] backup , int[] data , int e) {

        if (backup.length - 1 != data.length){ // Die Länge von backup[] sollte genau um 1 Element länger sein als data[]
            return false;
        }

        if (backup[0] != e) {   // e sollte am grossten Zahl in backup[] sein, und befindet sich genau 0 te index
            return false;
        }

        for (int v : data) {    // in data[] muss kein e vorhanden.
            if (v == e) {
                return false;
            }
        }

        return true;
     }

     /* Assert for Add. */
    private static boolean isAdded(int[] data , int sizeBeforeAdd) {

        if (data.length != sizeBeforeAdd + 1){ // Die Länge von backup[] sollte genau um 1 Element länger sein als bevor der Addition
            return false;
        }

        if (data[0] != Integer.MAX_VALUE) {   // MAX_VALUE sollte sich genau 0 te index befinden.
            return false;
        }

        return true;
     }
}
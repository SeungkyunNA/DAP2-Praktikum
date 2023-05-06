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
        heap.addAll(input);

        // int kCounter = 1;

        // for(int i = (input.length-1 / 2) ; i > 0 ; i --) {
        //     System.out.println("Befor MH : "+ Arrays.toString(heap.getValues()));
        //     heap.maxHeapify(i);
        //     System.out.println("After MH : "+ Arrays.toString(heap.getValues()));

        //     int temp = heap.heap[0];
        //     heap.heap[0] = heap.heap[input.length-kCounter ];
        //     heap.heap[input.length-kCounter] = temp;
        //     kCounter++;

        //     
            
        // }
        
        System.out.println("Add : "+ Arrays.toString(heap.getValues()));
        /* Test for Add , Extract , Peek, Extact and Add */

        // System.out.print("PeekMax :");
        // int exMax = heap.peekMax();
        // System.out.println("Max : " + exMax +" Rest : " +  Arrays.toString(heap.getValues()));
        

        // for(int i = 0 ; i < 2 ; i ++) {
        //     System.out.print("exMax :");
        //     exMax = heap.extractMax();
        //     System.out.println("Max : " + exMax +" Rest : " +  Arrays.toString(heap.getValues()));
        // }
        
        // heap.add(500);
        // heap.add(-245);
        // System.out.print("add 500 and -245 :");
        // System.out.println(Arrays.toString(heap.getValues()));


        assert(isHeap(heap.getValues()));

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
}



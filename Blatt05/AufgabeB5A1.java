import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB5A1{
    public static void main(String[] args) {

        /* Eingabe einlesen mit Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...) */
        int[] input; 
        try {
            input = readInput();
        } catch (NumberFormatException e){ 
            System.err.println("ERROR: Input is not valid."); 
            return;
        }

        /* Fehlerbehandlung fuer length = 0 */
        if (input.length == 0) {
            return;
        }

        /* Early return. if is length = 1, result is always [1] */
        if(input.length == 1) {
            int [] early = {1};
            System.out.println(Arrays.toString(early));
            return;
        }

        AufgabeB5A1 a = new AufgabeB5A1(input);
        System.out.println(Arrays.toString(a.count()));

    }
    /* StandardInput wird eingelesen und als Rückgabe ein int[] zurückgeben */
    public static int[] readInput() throws NumberFormatException {

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
    
    /* Class Object */

    public int[] data;

    public AufgabeB5A1(int[] data) {
        this.data = data;

    }

    /* Hilfsfunktion für Interval */
    public int getMin() {

        int min = data[0];
        for(int i : data){ 
            min = Math.min(min, i);
        }
        return min;

    }
    public int getMax() {

        int max = data[0];
        for(int i : data){ 
            max = Math.max(max, i);
        }
        return max;

    }

    public int[] count() {

        int max = this.getMax();
        int min = this.getMin();

        int[] result = new int[max - min + 1];

        for (int v : data){
            result[v - min]++;
        }
        return result;
    }
}

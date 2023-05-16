import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB5A2{
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
        System.out.println("Before sorting : " + Arrays.toString(input));

        AufgabeB5A2 a = new AufgabeB5A2(input);
        System.out.println("Frequencies : " + Arrays.toString(a.countingSort()));
        System.out.println("After sorting : " + Arrays.toString(a.data));
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
    public AufgabeB5A2(int[] data) {
        this.data = data;
    }

    public int[] countingSort() {

        AufgabeB5A1 f = new AufgabeB5A1(this.data);
        int[] save = f.count();
        int[] freq = Arrays.copyOf(save, save.length);

        int max = f.getMax();
        int dataPointer = 0;
        for (int si = freq.length-1 ; si >= 0 ; si --) {
            /* if Key not 0, write on Data current Max value and increse Key. 
               if was originaly 0 , skip for this Max value  */
            while (freq[si] != 0) {
                data[dataPointer] = max;
                freq[si]--;
                dataPointer++;
            }
            max --;
        }

        return save;
    }
}
    
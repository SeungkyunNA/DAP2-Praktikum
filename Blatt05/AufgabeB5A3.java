import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class AufgabeB5A3{
   public static void main(String[] args) {
      /* Eingabe einlesen mit Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...) */
      int[] input; 
      try {
          input = readInput();
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

      /* Argument überprüfen : Argument soll größer als 0 sein */
      if (k < 1) {
          System.err.println("ERROR: Not enough input values."); 
          return;
      }

      AufgabeB5A3 a = new AufgabeB5A3(input);
      
      int result = a.exactSelect(k);
      System.out.println(result);

   }
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
   public AufgabeB5A3(int[] data) {
      this.data = data;
   }

   public int exactSelect(int k) {

      /* Erstellen Interval-groess Array und False Init */
      int max = this.getMax();
      int min = this.getMin();
      boolean[] contain = new boolean[max - min + 1]; 

      /* wenn k groesser als Interval  */
      if (k > contain.length) {
         throw new IllegalArgumentException("k is too big");
      }

      /* in Boolean Array markiert, welche Wert existeirt */
      for (int v : data) {
         contain[v-min] = true;
      }

      int trueCount = 0;
      for (boolean b : contain) {
         if (b) {trueCount++;}
      }

      /* Iteration auf Boolean Array, wenn True trifft, increment k um 1. */
      int kCount = k;
      for (int i = 0 ; i < contain.length ; i ++) {
         if (contain[i]){
            kCount--;
         }

         if (kCount == 0) {
            return i+min;
         }
      }

      /* Test Duplicate. */
      HashSet<Integer> set = new HashSet<>();
      for (int i : data) {
         set.add(i);
      }
   
      throw new IllegalArgumentException("k is too large | k : " + k + " input.length : " + data.length + " Sum of Trues : " + trueCount + " Set size : " + set.size());
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
}
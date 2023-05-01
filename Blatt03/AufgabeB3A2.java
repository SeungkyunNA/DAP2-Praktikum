import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB3A2 {

   public static void main(String[] args) {
      int[] result;
      try{
         result = readStandardIn();
      } catch (NumberFormatException e){  // Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...)
         System.err.println("ERROR: Input is not valid."); 
         return;
      }

      qSort(result);

      if(hasDuplicate(result)){  // Fehlerbehandlung : Duplikation.
         System.err.println(("Error: Duplicate entries in input"));
         return;
      }
      
      if (args.length == 0) { // Fehlerbehandlung : wenn k nicht eingegeben wird.
         System.err.println("ERROR: The argument must be at least one natural number.");
         return;
      }

      int[] ki = new int[args.length];
      try {
         for (int i = 0 ; i < args.length ; i++) {
            ki[i] = Integer.parseInt(args[i]);
            if (ki[i] < 0) {
               System.err.println(("Error: The argument must be natural number."));
               return;
            }

            if (ki[i] > calFac(result.length)) {
               System.err.println(("Error: The argument is too large"));
               return;
            }
         }
      } catch (NumberFormatException e) { // Fehlerbehandlung : wenn k nicht eine Natuerliche Zahl ist. ("1.1" , "1 2 a")
         System.err.println("ERROR: The arguments must be natural number.");
         return;
      }

      System.out.println("Sorted input :");
      System.out.println(Arrays.toString(result));

      AufgabeB3A2 a = new AufgabeB3A2(result);
      for(int ks : ki) {
         System.out.println("The " + ks + "-smallest permutation is:");
         int[] erg = a.choosePermutation(ks);
         if(erg != null) {
            System.out.println(Arrays.toString(erg));
         }

      }

   }

   public static int[] readStandardIn() throws NumberFormatException {

      /* Prinzipiel ist aus uebung. (dynamische Eingabespeicherung mit statische Datenstructur.) */

      Scanner sc = new Scanner(System.in);
      
      int[] a = new int[1];
      int i = 0;
      while(sc.hasNextLine()) {
         
         double doubleValue = Double.parseDouble(sc.nextLine());
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

   /* Hilfsfunktion Qsort. aus Blatt02 */

   public static int partition(int[] data , int l , int r) {

      int pivot = data[l];
      int pivot_pos = l;
      int iterator = l + 1;
      int right_area = r;

      while(iterator <= right_area) {
         if (data[iterator] < pivot) {
            int save = data[pivot_pos];
            data[pivot_pos] = data[iterator];
            data[iterator] = save;
            pivot_pos++;
            iterator++;

         } else if (data[iterator] > pivot) {
            int save = data[iterator];
            data[iterator] = data[right_area];
            data[right_area] = save;
            right_area--;

         } else {
            iterator++;
         }
      }
      return pivot_pos;
   }

   public static void qSort(int[] data , int l , int r) {

      if (l < r) {
         int a = partition(data , l , r);
      
         qSort(data , l , a);
         qSort(data , a+1 , r);
      }

   }

   public static void qSort(int[] data) {

      qSort(data , 0 , data.length -1);
      
   }
   /* Hilfsfunktion Injektion fuer Duplikation.*/
   public static boolean hasDuplicate(int[] data) {
      for (int i = 1 ; i < data.length ; i++) {
          if (data[i-1] == data[i]) {
              return true;
          }
      }   
      return false;
  }

  /* Hilfsfunktion. gibt n! zurueck */
  public static int calFac(int n) {
   if(n == 0) {
       return 1;
   }
   int fac = n;
   for(int i = n-1 ; i >= 1 ; i--) {
       fac *= i;
   }
   return fac;

}


   public int[] data;
   public AufgabeB3A2(int[] data) {
      this.data = data;
   }
   public int[] choosePermutation(int kSmallest) {
      if (kSmallest <= 0) {
         return null;
      }
      int kSmall = kSmallest-1; // Beruecksichrigen fuer Index. (17te kleintes Zahl steht 16te Index.)
      int[] temp = new int[data.length];
      int[] copy = Arrays.copyOf(data , data.length);

      int first = calFac(data.length - 1);
      
      for (int n = 0 ; n < temp.length ; n++) {
         temp[n] = copy[kSmall/first];
         
         copy = reduceArr(copy, kSmall/first);
         kSmall = kSmall%first;
         first = calFac(copy.length - 1);
      }

      return temp;
   }

   public int[] reduceArr(int[] data2 , int idx) {

      int[] temp = new int[data2.length-1];
      int a = 0;
      int b = 0;
      while(a < temp.length) {
         if (b == idx) {
            b++;
         }
         temp[a] = data2[b];
         a++;
         b++;
      }
      return temp;
   }
    
}

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class AufgabeB4A2 {
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
        Instant start;
        Instant finish;
        long time = 0;
        Random r = new Random();
        int find = r.nextInt(input.length-1);


        /* Zeitmessung für quickSelectFirst */
        int aqsf=0;

        for (int i=0;i<20;i++){
            shuffle(input,input.length);

            start = Instant.now();
            aqsf += quickSelectFirst(input, find);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }

        long qsf = time/20;
        time = 0;

        /* Zeitmessung für quickSelectRandom */
        int aqsr=0;
        start = Instant.now();
        for (int i=0;i<20;i++){
            shuffle(input,input.length);

            start = Instant.now();
            aqsr += quickSelectRandom(input, find);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }
        
        long qsr = time/20;
        time = 0;

        /* Zeitmessung für heapSelect */
        int ahs=0;
       
        for (int i=0;i<20;i++){
            shuffle(input,input.length);

            start = Instant.now();
            ahs += heapSelect(input, find);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }

        long hs = time/20;
        time = 0;

        /* Zeitmessung für heapSelectFast */
        int ahsf=0;

        for (int i=0;i<20;i++){
            shuffle(input,input.length);

            start = Instant.now();
            ahsf += heapSelectFast(input, find);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }

        long hsf = time/20;
        time = 0;

        System.out.println("________________________________________________");
        System.out.println("#CASE 1");
        System.out.println("Input : "+input.length+" unsorted integers. Inputs are shuffled every iteration. for 'k' also random choose");
        System.out.println();
        System.out.println("QuickSelcetFirst (Average of 20 times):  " + qsf + "ms | result : " + aqsf/20 + " is " + find + "-smallst number" );
        System.out.println("QuickSelcetRandom (Average of 20 times): " + qsr + "ms | result : " + aqsr/20 + " is " + find + "-smallst number" );
        System.out.println("heapSelect (Average of 20 times):         " + hs + "ms | result : " + ahs/20 + " is " + find + "-smallst number" );
        System.out.println("heapSelectFast (Average of 20 times):    " + hsf + "ms | result : " + ahsf/20 + " is " + find + "-smallst number" );
        System.out.println("________________________________________________");

        Arrays.sort(input);
        find = r.nextInt(input.length-1);

        /* Zeitmessung für quickSelectFirst with sorted Input */
        aqsf=0;

        for (int i=0;i<20;i++){
            start = Instant.now();
            aqsf += quickSelectFirst(input, input.length);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }

        qsf = time/20;
        time = 0;

        /* Zeitmessung für quickSelectRandom with sorted Input */
        aqsr=0;
        start = Instant.now();
        for (int i=0;i<20;i++){
            start = Instant.now();
            aqsr += quickSelectRandom(input, input.length);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }
        
        qsr = time/20;
        time = 0;

        /* Zeitmessung für heapSelect with sorted Input */
        ahs=0;
        
        for (int i=0;i<20;i++){
            start = Instant.now();
            ahs += heapSelect(input, input.length);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }

        hs = time/20;
        time = 0;

        /* Zeitmessung für heapSelectFast with sorted Input */
        ahsf=0;

        for (int i=0;i<20;i++){
            start = Instant.now();
            ahsf += heapSelectFast(input, input.length);
            finish = Instant.now();
            time += Duration.between(start, finish).toMillis();
        }

        hsf = time/20;
        time = 0;
        System.out.println("________________________________________________");
        System.out.println("#CASE 2");
        System.out.println("Input : "+input.length+" sorted integers and find the biggest number. (Worst case for an algorithm that chooses first pivot)");
        System.out.println();
        System.out.println("QuickSelcetFirst (Average of 20 times):  " + qsf + "ms | result : " + aqsf/20 + " is " + input.length + "-smallst number" );
        System.out.println("QuickSelcetRandom (Average of 20 times): " + qsr + "ms | result : " + aqsr/20 + " is " + input.length + "-smallst number" );
        System.out.println("heapSelect (Average of 20 times):         " + hs + "ms | result : " + ahs/20 + " is " + input.length + "-smallst number" );
        System.out.println("heapSelectFast (Average of 20 times):    " + hsf + "ms | result : " + ahsf/20 + " is " + input.length + "-smallst number" );
        System.out.println("________________________________________________");

        shuffle(input,input.length);
        find = r.nextInt(input.length-1);


        for (int i = 0 ; i < input.length/2 ; i ++) {
            input[i] = i * -1;
        }

                /* Zeitmessung für quickSelectFirst with sorted Input */
                aqsf=0;

                for (int i=0;i<20;i++){
                    start = Instant.now();
                    aqsf += quickSelectFirst(input, find);
                    finish = Instant.now();
                    time += Duration.between(start, finish).toMillis();
                }
        
                qsf = time/20;
                time = 0;
        
                /* Zeitmessung für quickSelectRandom with sorted Input */
                aqsr=0;
                start = Instant.now();
                for (int i=0;i<20;i++){
                    start = Instant.now();
                    aqsr += quickSelectRandom(input, find);
                    finish = Instant.now();
                    time += Duration.between(start, finish).toMillis();
                }
                
                qsr = time/20;
                time = 0;
        
                /* Zeitmessung für heapSelect with sorted Input */
                ahs=0;
                
                for (int i=0;i<20;i++){
                    start = Instant.now();
                    ahs += heapSelect(input, find);
                    finish = Instant.now();
                    time += Duration.between(start, finish).toMillis();
                }
        
                hs = time/20;
                time = 0;
        
                /* Zeitmessung für heapSelectFast with sorted Input */
                ahsf=0;
        
                for (int i=0;i<20;i++){
                    start = Instant.now();
                    ahsf += heapSelectFast(input, find);
                    finish = Instant.now();
                    time += Duration.between(start, finish).toMillis();
                }
        
                hsf = time/20;
                time = 0;
                System.out.println("________________________________________________");
                System.out.println("#CASE 3");
                System.out.println("The first half of the input are negative numbers that are sorted, and the other half are positive numbers that are randomly shuffled.");
                System.out.println();
                System.out.println("QuickSelcetFirst (Average of 20 times):  " + qsf + "ms | result : " + aqsf/20 + " is " + find + "-smallst number" );
                System.out.println("QuickSelcetRandom (Average of 20 times): " + qsr + "ms | result : " + aqsr/20 + " is " + find + "-smallst number" );
                System.out.println("heapSelect (Average of 20 times):         " + hs + "ms | result : " + ahs/20 + " is " + find + "-smallst number" );
                System.out.println("heapSelectFast (Average of 20 times):    " + hsf + "ms | result : " + ahsf/20 + " is " + find + "-smallst number" );
                System.out.println("________________________________________________");


    }
    public static int partition(int[] arr, int l, int r, int p) {
    
        if (arr.length <= 2){
            return arr[p];
        }

        
        int pivot = arr[p];   // Random erzeugte Zahl wird Pivotposition addiert.
        int pivot_pos = p;
        int iterator = l;
        int right_area = r;

        while(iterator <= right_area) {

            if (arr[iterator] > pivot && iterator > pivot_pos) { /* Wenn Iterator vor der Position des Pivot ist, tauschen wir nicht (gehe zu else Fall) */
                int save = arr[pivot_pos];
                arr[pivot_pos] = arr[iterator];
                arr[iterator] = save;
                pivot_pos++;
                iterator++;

            } else if (arr[iterator] < pivot) {
                int save = arr[iterator];
                arr[iterator] = arr[right_area];
                arr[right_area] = save;
                if (right_area == pivot_pos) {
                    pivot_pos = iterator;
                }
                right_area--;

            } else {
                iterator++;
            }
        }
        //printPartiotion(arr , l , r , pivot_pos); /* Only for Test. print result of Patitioning */

        return pivot_pos;
    }

    /* Quickselect First */
    public static int quickSelectFirst(int[] arr, int k) {

        /* Fehlerbehandlung fuer leere Array */
        if(arr.length == 0) {
            throw new IllegalArgumentException();
        }
        /* Early return fuer 1-laenge Array */
        if(arr.length == 1) {
            return arr[0];
        }

        int[] copy = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            copy[i] = arr[i];
        }

        /* Setze Variablen fuer linke und rechte Schranke */
        int left = 0; 
        int right = copy.length-1;

        /* Partitionierung mit Schleife. Hier wird immer erste Element von Teilmenge ausgewaelt. */

        int pivotPos = partition(copy, left, right, left);
        while (pivotPos != copy.length-k) {

            if (pivotPos > copy.length-k) {                   /* Wenn k in "K" Teilarray steht */
                right = pivotPos - 1;
                pivotPos = partition(copy, left, right, left);
            } else if (pivotPos == copy.length-k) {           /* Wenn k in "M" Teilarray steht */
                return copy[pivotPos];
            } else {                                /* Wenn k in "G" Teilarray steht */
                left = pivotPos + 1;
                pivotPos = partition(copy, left, right, left);
            }  
        }

        return copy[pivotPos];
    }

    /* Quickselect Random */
    public static int quickSelectRandom(int[] arr, int k) {
        /* Fehlerbehandlung fuer leere Array */
        if(arr.length == 0) {
            throw new IllegalArgumentException();
        }
        /* Early return fuer 1-laenge Array */
        if(arr.length == 1) {
            return arr[0];
        }

        /* Setze Variablen fuer linke und rechte Schranke */
        int left = 0; 
        int right = arr.length-1;

        /* Random number class erzeugen */
        Random r = new Random();
        int random;

        /* Wenn weniger als 2 Element in Array gibt, random ist 0. */
        if (right - left <= 1) {
            random = 0;
        } else {
            random = r.nextInt(right - left - 1);
        } 

        int[] copy = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            copy[i] = arr[i];
        }

        /* Partitionierung mit Schleife. Hier wird immer erste Element von Teilmenge ausgewaelt. */

        int pivotPos = partition(copy, left, right, left + random);

        while (pivotPos != copy.length-k) {

            if (pivotPos > copy.length-k) {                   /* Wenn k in "G" Teilarray steht */
                right = pivotPos - 1;
                if (right - left <= 2) { /* Wenn weniger als 2 Element in Array gibt, random ist 0. */
                    random = 0;
                } else {
                    random = r.nextInt(right - left - 1) ;
                } 

                pivotPos = partition(copy, left, right, left + random); 

            } else if (pivotPos == copy.length-k || left == right) {           /* Wenn k in "M" Teilarray steht */
                
                return copy[pivotPos];

            } else {                                                /* Wenn k in "K" Teilarray steht */
                left = pivotPos + 1;
                if (right - left <= 2) { /* Wenn weniger als 2 Element in Array gibt, random ist 0. */
                    random = 0;
                } else {
                    random = r.nextInt(right - left - 1);
                } 

                pivotPos = partition(copy, left, right, left+random);
            }  
        }
        return copy[pivotPos];
    }

    /* Heapselect aus A1 */
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
    /* HeapselectFast aus A1 */
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
    /* Hilfsfunktion. Partition ausdruecken */
    public static void printPartiotion(int[] arr , int l , int r , int pivot_pos) {
        System.out.println();
        for (int i = l ; i <= r ; i++ ){
            if (i < pivot_pos) {
                if (i == l) {
                    System.out.print("K : [" + arr[i]);
                } else {
                    System.out.print(", " + arr[i]);
                }
                if (i == pivot_pos-1){
                    System.out.print("] | ");
                }
                
            } else if (i == pivot_pos) {
                System.out.print("M : [" + arr[i] + "] idx : "+i+"| "  );

            } else {
                if (i == pivot_pos+1) {
                    System.out.print("G : [" + arr[i]);
                } else {
                    System.out.print(", " + arr[i]);
                }
                if (i == r) {
                    System.out.print("] ");
                }
            }
        }
        System.out.println();
    }
    /* Hilfsfunktion. Gibt ein zufällig neu angeordnetes Array zurück.*/
    public static void shuffle(int[] array, int count){
		int temp, temp2, randomNum1, randomNum2;
		
		for(int i=0; i<count; i++){
			randomNum1 = (int)(Math.random()*array.length);
			temp = array[randomNum1];
			randomNum2 = (int)((Math.random()*array.length));
			temp2 = array[randomNum2];
			array[randomNum1] = temp2;
			array[randomNum2] = temp;
		}
	}


}

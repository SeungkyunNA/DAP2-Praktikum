import java.util.Arrays;
import java.util.Scanner;
public class AufgabeB6A1{
    public static void main(String[] args) {
        /* Eingabe einlesen mit Fehlerbehandlung : wenn ungueltige Eingabe gegeben wird. (z.B. "a" , " " ...) */
        int[] input; 
        try {
            input = readInput();
        } catch (NumberFormatException e){ 
            System.err.println("ERROR: Input is not valid."); 
            return;
        }

        if(input.length == 0) {
            System.err.println("No Input");
            return;
        }

        System.out.println("Before Sorting : " + Arrays.toString(input));
        AufgabeB6A1 lsd = new AufgabeB6A1(input);
        AufgabeB6A1 msd = new AufgabeB6A1(input);
        lsd.lsdRadix();
        msd.msdRadix();

        assert(isSort(lsd.data));
        assert(isSort(msd.data));
        System.out.println("Sort After LSD : " + Arrays.toString(lsd.data));
        System.out.println("Sort After MSD : " + Arrays.toString(msd.data));
        
    }

    /* Class Object */
    public int[] data;
    public AufgabeB6A1(int[] data) {
        this.data = data;

    }
    public void sortByByte(int l, int r, int b) {
        int[] byteSave = new int[r - l + 1]; 

        /* Erstelle ein Array ( C ) fuer die Heufigkeit des Schluessels "count()" */
        /* Danach "countToIdx()" addieret je Schluessel miteinander, um position zu stimmen ( C' )  */
        int[] c_freq = count(data,l,r,b);    
        int[] freq = countToIdx(c_freq);

        for (int j = data.length-1 ; j >= 0 ; j --) {
            int q = (data[l+j] >> 8*b) & 0xFF; // ermittle den Schlüssel q von A[j]
            byteSave[freq[q]-1] = data[l+j];   // speichere in Hilfsarray entsprechende Werte
            freq[q]--;                         // Position um 1 verringen
        }

        for (int idx = l ; idx < r+1 ; idx ++) {
            data[idx] = byteSave[idx];         // Sortierung wird in Hilfsarray fertig. kopieren wir zu data[]
        }

    }
    public void lsdRadix() {
        for(int i = 0 ; i < 4 ; i ++) {        // b = 0 ist niederwerttigte Byte
            sortByByte(0, data.length-1, i);    
        }
    }
    
    
    
    public void msdRadix(int l, int r, int b) {

        sortByByte(l, r, b);


    }

    public void msdRadix() {
    
        msdRadix(0,  this.data.length-1, 3);
    }


     /* StandardInput wird eingelesen und als Rückgabe ein int[] zurückgeben */
    public static int[] readInput() throws NumberFormatException {
    
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

    /* Hilfsfunktion für Freq.Array */
    public int[] count(int[] d , int l , int r, int b) {

        int[] result = new int[256];

        for (int v = l ; v < r+1 ; v++){
            int shift = (data[v] >> 8*b) & 0xFF;
            result[shift]++;
        }
        return result;
    }

    /* Hilfsfunktion für Freq to Idxpostion marker */
    public int[] countToIdx(int[] freq) {

        int[] result = Arrays.copyOf(freq, freq.length);

        for (int i = result.length-1 ; i > 0 ; i--){
            result[i-1] = result[i-1] + result[i];
        }

        return result;
    }

    /* Assert für absteigene Sortierung */
    public static boolean isSort(int[] c) {

        for (int i = 0 ; i < c.length-1 ; i ++) {
            if (c[i] < c[i+1]) {
                return false;
            }
        }
        return true;
    }
    
}
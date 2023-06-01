public class AufgabeB7A1{
    public static void main(String[] args) {

        /* Argument ueberpruefen, ob es richtig eingegeben */
        if(args.length == 0) { 
            System.err.println("No Argument");
            return;
        }

        /* Argument ueberpruefen, ob es ein INT ist. */
        int k;
        try {   
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            
            System.err.println("Argument must be a number");
            return;
        }

        System.out.println(fibDyn(k));

    }
    public static int fibDyn(int n) {
        
        /* Negative Eingabe , 0 , 1 geben zurueck */
        if (n <= 1) {
            return n;
        }

        /* Ergebnis speicher fure gerade Zahl(result[0]) und ungerade Zahl (result[1]) */
        int[] result = new int[2];
        
        /* Init */
        result[0] = 1;
        result[1] = 1;
        
        /* 2 bis n wird addiert. Danach speichert in result[n%2] */
        for (int i = 2 ; i < n ; i++){
            result[(i+1)%2] += result[i%2];
        }

        return result[n%2];
    }
}
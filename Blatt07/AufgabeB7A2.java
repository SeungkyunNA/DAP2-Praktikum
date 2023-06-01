import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AufgabeB7A2 {
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

        /* Argument ueberpruefen, ob es ein positive INT ist. */
        if (k < 0) {
            System.err.println("Argument must be a Positiv number");
            return;
        }

        /* Input Einlesen */
        Scanner sc = new Scanner(System.in);
        int[] weight;
        int[] value;

        try {
            value = getInput(sc);
            weight = getInput(sc);
        } catch (NumberFormatException e) {
            System.err.println("Input must be a Positiv number");
            return;
        }

        /* Input Gueltigkeit ueberpruefen */
        if(weight.length != value.length) {
            System.err.println("The number of values does not match the number of weights.");
            return;
        }

        /* Rest von Input ueberpruefen */
        if(sc.hasNext()) {
            System.err.println("Input did not end after second list.");
            return;
        }

        /* Aufgabe start */
        int[][] result = knapsack(value, weight, k);

        /* Antwort ist genau zwichenspeicher[i][j] gespcheirt. */
        System.out.println(result[value.length][k]);

    }
    public static int[] getInput(Scanner scanner) throws NumberFormatException{

        /* Temp speicher */
        List<Integer> temp = new ArrayList<Integer>();
        
        /* Eingabe lesen, bis Leerzeile treffen */
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            
            if (s == "") { // Leerzeile pruefen
                break;
            }

            temp.add(Integer.parseInt(s));
        }

        /* Kopie zu Array */
        int[] result = new int[temp.size()];
        for (int i = 0 ; i < result.length ; i++) {
            result[i] = temp.get(i);
        }

        return result;

    }

    public static int[][] knapsack(int[] values, int[] weights, int capacity){
        
        /* Erstellen wir 2D-Array fuer zwichenspeicher. Aber wir brauchen zusätzlich ein Spalte und Zeile mehr */
        /* Um "Self reference" nicht zu beschädigen  */
        int [][] result = new int[values.length+1][capacity+1];

        /* For schleife wird dann nicht (0 .. n-1) sondern (1 .. n) durchlaufen */
        /* Damit representiert es nicht "n te Index von Input", sondern "n te Element von Input" */
        for (int c = 1 ; c <= capacity ; c ++) {    
            for (int i = 1 ; i <= values.length ; i ++) { 
                
                if (weights[i-1] <= c) {
                    /* Falls, aktuelle Element kann in Backpack eingepackt werden (Weights ist noch nicht voll) */
                    result[i][c] = Math.max(values[i-1] + result[i-1][c-weights[i-1]], result[i-1][c]);
                } else {
                    /* Falls, Weights ist nicht genug fuer aktuelle Gegenstand zu einpacken  */
                    result[i][c] = result[i-1][c];
                }
            }
        }
        return result;
    }
}

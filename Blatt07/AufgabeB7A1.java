public class AufgabeB7A1{
    public static void main(String[] args) {

        if(args.length == 0) {
            System.err.println("No Argument");
            return;
        }
        int k;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument must be a number");
            return;
        }
        if (k <= 1) {
            System.out.println(k);
            return;
        }

        System.out.println(fibDyn(k));

    }
    public static int fibDyn(int n) {
        if (n <= 1) {
            return n;
        }

        int[] result = new int[2];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2 ; i < n ; i++){
            result[(i+1)%2] += result[i%2];
        }
        return result[n%2];
    }
}
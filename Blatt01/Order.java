public class Order {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Exactly two arguments are required.");
            return;
        }
        int min = 0;
        int max = 0;
        
        try {
            min = Integer.parseInt(args[0]);
            max = Integer.parseInt(args[1]);
            if (max < min) {
            int h = min;
            min = max;
            max = h;
            }
        } catch (NumberFormatException e) {
            System.out.println("The arguments must be integers.");
            return;
        }
        System.out.println(min + " is not larger than " + max);
        
    }
}

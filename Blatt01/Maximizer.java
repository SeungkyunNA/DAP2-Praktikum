import java.util.Scanner;

class Maximizer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int max = 0;
        while (input.hasNextLine()) {
            try {
                int value = Integer.parseInt(input.nextLine());
                max = (value > max) ? value : max;
            } catch (NumberFormatException e) {
                System.err.println("Input list contains a non-number.");
                return;
            }
        }
        System.out.println("The largest number was " + max + ".");
    }
}
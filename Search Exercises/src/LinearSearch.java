import java.util.ArrayList;
import java.util.Scanner;

public class LinearSearch {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();


        Scanner input = new Scanner(System.in);
        int length, search, c;

        System.out.println("enter how many numbers you want to enter");
        length = input.nextInt();

        for (int i = 0; i < length; i++) {
            int numbersLeft = length - i;
            System.out.println("enter " + numbersLeft + " numbers here");
            numbers.add(input.nextInt());
        }

        System.out.println("Enter a value to find");
        search = input.nextInt();

        for (c = 0; c < length; c++) {
            if (numbers.get(c) == search) {
                System.out.println(search + " is present at index " + c);
                break;
            }
        }
        if (c == length) {
            System.out.println(search + " is not present in array");
        }


        System.out.println(numbers);

        //System.out.println(numbers[0]);
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();


        Scanner input = new Scanner(System.in);
        int length, search;

        System.out.println("enter how many numbers you want to enter");
        length = input.nextInt();

        for (int i = 0; i < length; i++) {
            int numbersLeft = length - i;
            System.out.println("enter " + numbersLeft + " numbers here");
            numbers.add(input.nextInt());
        }
        Collections.sort(numbers);

        System.out.println("Enter a value to find");
        search = input.nextInt();

        int c = Collections.binarySearch(numbers,search);
        if(c>=0){
            System.out.println(search + " found at index " + c);
        } else{
            System.out.println(search + " not found");
        }


        System.out.println(numbers);

        //System.out.println(numbers[0]);
    }
}


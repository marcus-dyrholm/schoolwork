import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean connect = true;

        try {


            // Create a socket to connect to the server
            Socket connectToServer = new Socket("localhost", 7999);

            // Create an input stream to receive data from the server
            DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());

            // Create an output stream to send data to the server
            DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
            while (connect) {
                // Enter annual interest rate
                System.out.print("Enter interest rate per year: ");
                double interestPerYear = input.nextDouble();
                osToServer.writeDouble(interestPerYear);

                // Enter number of years
                System.out.print("Enter number of years ");
                int years = input.nextInt();
                osToServer.writeInt(years);

                // Enter loan amount
                System.out.print("Enter loan amount, for example, 120000,95: ");
                double loanAmount = input.nextDouble();
                osToServer.writeDouble(loanAmount);

                osToServer.flush();

                // Get monthly payment from the server
                double monthlyPayment = isFromServer.readDouble();

                double yearlyPayment = isFromServer.readDouble();

                // Get total payment from the server
                double totalPayment = isFromServer.readDouble();

                System.out.println("Annual Interest Rate: " + interestPerYear +
                        "\nNumber of Years: " + years + "\nLoan Amount: " +
                        loanAmount + "\n");
                System.out.println("monthlyPayment: " + monthlyPayment + " " +"\nYearly payment "+ yearlyPayment +
                        "\ntotalPayment: " + totalPayment + '\n');
                //Exit or continue with a new set of values
                System.out.print("Type yes to continue with a new set of value or no to stop: ");

                if (input.next().equals("no")) {
                    System.out.println("Disconecting you from the server");
                    connect = false;
                }
            }
            input.close();
            connectToServer.close();
        }
        catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }

}



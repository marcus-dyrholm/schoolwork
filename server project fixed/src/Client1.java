import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean connect = true;

        try {
            Socket socket = new Socket("localHost", 8000);

            DataInputStream inFromServer = new DataInputStream(socket.getInputStream());
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());

            while (connect) {
                System.out.println("Enter loan amount here: ");
                double loan = input.nextDouble();
                outToServer.writeDouble(loan);

                System.out.println("Enter years here: ");
                double years = input.nextDouble();
                outToServer.writeDouble(years);

                System.out.println("Enter interestrate here: ");
                double interestrate = input.nextDouble();
                outToServer.writeDouble(interestrate);

                outToServer.flush();

                double monthlyInterest = inFromServer.readDouble();
                double monthlyPayment = inFromServer.readDouble();
                double totalPayment = inFromServer.readDouble();

                System.out.println("Loan = " + loan + "\nNumber of years = " + years + "\nAnnual interest rate = " + interestrate + "%");
                System.out.println();
                System.out.println("Monthly interest of loan = " + monthlyInterest + "\nMonthly payment of loan = " + monthlyPayment + "\nTotal Payment of loan = " + totalPayment);
                System.out.println();
                System.out.println("Type yes to continue with a new loan or no to close server connection");
                if (input.next().equals("no")) connect = false;
            }
            input.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

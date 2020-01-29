import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LoanClient {

    public static void main(String[] args) {
        int port = 8000;
        String host = "localHost";

        int years;
        int interestRate;
        int loan;

        Socket socket;

        System.out.println("enter years here");
        Scanner inputYears = new Scanner(System.in);
        years = inputYears.nextInt();

        System.out.println("enter interestrate");
        Scanner inputRate = new Scanner(System.in);
        interestRate = inputRate.nextInt();

        System.out.println("enter loan");
        Scanner inputLoan = new Scanner(System.in);
        loan = inputLoan.nextInt();



        try{
            System.out.println("connecting");
            socket=new Socket(host, port);
            System.out.println("connected");
            DataInputStream inputStream;
            DataOutputStream outputStream;
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(years);
            outputStream.writeDouble(interestRate);
            outputStream.writeDouble(loan);
            outputStream.flush();
            inputStream = new DataInputStream(socket.getInputStream());

            double monthlyPayment = inputStream.readDouble();
            double totalPayment = inputStream.readDouble();

            System.out.println(monthlyPayment);
            System.out.println(totalPayment);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

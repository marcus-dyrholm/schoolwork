import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class HandleAClient implements Runnable {
    private Socket socket;
    private int years;
    private int rate;
    private int loan;
    private double monthlyPayment;
    private double monthlyInterestRate;
    private int totalPayment;

    public HandleAClient(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while(true){
                years = in.readInt();
                rate = in.readInt();
                loan = in.readInt();

                monthlyInterestRate=rate/1200;
                monthlyPayment = loan * monthlyInterestRate / (1-(1/Math.pow(1+monthlyInterestRate,years*12)));

                double totalPayment = monthlyPayment * years * 12;

                out.writeDouble(monthlyPayment);
                out.writeDouble(totalPayment);

                System.out.println("Annual Interest Rate: " + rate +
                        "\nNumber of Years: " + years + "\nLoan Amount: " +
                        loan + "\n");
                System.out.println("monthlyPayment: " + monthlyPayment + " " +
                        "\ntotalPayment: " + totalPayment + '\n');
            }

        }catch  (Exception e) {
            e.printStackTrace();

        }

    }
}



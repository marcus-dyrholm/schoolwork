import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int clientNo;

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                System.out.println("server started");

                while (true) {
                    Socket socket = serverSocket.accept();
                    clientNo++;
                    System.out.println("starting thread for client " + clientNo);
                    new Thread(new HandleAClient(socket)).start();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

    }

    static class HandleAClient implements Runnable {
        private Socket socket;

        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    double loan = inputFromClient.readDouble();
                    System.out.println("Client " + clientNo + " loan amount " + loan);
                    double years = inputFromClient.readDouble();
                    System.out.println("Client " + clientNo + " years " + years);
                    double interestRate = inputFromClient.readDouble();
                    System.out.println("Client " + clientNo + " interestRate " + interestRate + "%");

                    double monthlyInterest = interestRate / 1200;
                    System.out.println("Monthly interest computed for client " +clientNo + " " + monthlyInterest);
                    double monthlyPayment = loan * monthlyInterest / (1 - (1 / Math.pow(1 + monthlyInterest, years * 12)));
                    System.out.println("Monthly payment computed for client "+clientNo + " " + monthlyPayment);
                    double totalPayment = monthlyPayment * years * 12;
                    System.out.println("Total payment computed for client " + clientNo + " " + totalPayment);

                    outputToClient.writeDouble(monthlyInterest);
                    outputToClient.writeDouble(monthlyPayment);
                    outputToClient.writeDouble(totalPayment);


                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}

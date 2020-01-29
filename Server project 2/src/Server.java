import java.io.IOException;
import java.net.*;
import java.io.*;

public class Server {


    public static void main(String[] args) {
        //public void startServer () {
            new Thread(() -> {
                try {
                    ServerSocket ss = new ServerSocket(8000);
                     int clientNo = 0;
                     System.out.println("Server started");


                    while (true) {
                        Socket s = ss.accept();
                        clientNo++;
                        System.out.println("Starting thread for client " + clientNo);
                        InetAddress inetAddress = s.getInetAddress();
                        System.out.println("Client " + clientNo + "host name is " + inetAddress.getHostName());
                        System.out.println("Client " + clientNo + "ip address is " + inetAddress.getHostAddress());

                        new Thread(new HandleAClient(s)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
//}

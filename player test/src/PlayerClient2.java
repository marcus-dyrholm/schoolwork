import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlayerClient2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name = "";
        int playerNumber = 0;
        boolean connect = true;
        boolean playerInfoSet = false;
        boolean gameReady = false;
        boolean isPlayersTurn = false;
        try {
            // Create a socket to connect to the server
            Socket connectToServer = new Socket("localhost", 8000);
            // Create an input stream to receive data from the server
            DataInputStream inputFromServer = new DataInputStream(connectToServer.getInputStream());
            // Create an output stream to send data to the server
            DataOutputStream outputToServer = new DataOutputStream(connectToServer.getOutputStream());
            while (connect) {
                if(!playerInfoSet) {
                    // get the player number from the server
                    playerNumber = inputFromServer.readInt();
                    // Enter the player's name
                    System.out.print("Enter your name player " + playerNumber + ": ");
                    name = input.nextLine();

                    // Send the player name to the server
                    outputToServer.writeUTF(name);

                    // get the message from the server welcoming the player
                    String serverMessage = inputFromServer.readUTF();
                    System.out.println(serverMessage);
                    playerInfoSet = true;
                    outputToServer.flush();
                }
                // read the input that the game is ready to start
                gameReady = inputFromServer.readBoolean();
                if(gameReady) {
                    isPlayersTurn = inputFromServer.readBoolean();
                    if(isPlayersTurn) {
                        // ask the user to write his move
                        System.out.println("it's your turn " + name + " - These are your cards. Pick one of them:");
                        String move = input.nextLine();
                        // tell the server that the move has been made
                        outputToServer.writeUTF(move);
                        isPlayersTurn = false;
                        outputToServer.flush();
                    }
                    // else if((input.nextLine() != null)) System.out.println("Not your turn to play");
                    else outputToServer.flush();
                }

                // send the connect boolean to the client player handler
                outputToServer.writeBoolean(connect);
                // flush the output to the server data stream
                outputToServer.flush();
            }
            input.close();
            connectToServer.close();
        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }
    }
}



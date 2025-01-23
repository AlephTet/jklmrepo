import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "100.36.119.115";
    private static final int SERVER_PORT = 443;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server at " + SERVER_IP + ":" + SERVER_PORT);
            System.out.println("Type commands to interact with the server. Type 'exit' to close the connection.");

            while (true) {
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                out.println(command);

                String response = in.readLine();
                if (response == null) {
                    System.out.println("Server closed the connection.");
                    break;
                }
                System.out.println("Server response: " + response);

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the client.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to connect to the server. Please check the IP address and port.");
            e.printStackTrace();
        }
    }
}

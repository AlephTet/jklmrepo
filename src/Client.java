import java.io.*;
import java.net.Socket;

public class Client {

    private static final String SERVER_ADDRESS = "192.168.1.153";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        System.out.println("Connecting to the server...");

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

            System.out.println("Connected to the server.");
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Server response: " + response);
            }

        } catch (Exception e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
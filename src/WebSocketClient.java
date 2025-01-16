import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;

public class WebSocketClient {
    public static void main(String[] args) {
        // Replace with your WebSocket server URI
        String serverUri = "wss://phoenix.jklm.fun/socket.io/?EIO=4&transport=websocket";
        
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();
        
        // Build and connect the WebSocket
        WebSocket webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create(serverUri), new WebSocketListener())
                .join();
        

        // Start a thread for user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a message to send: ");
            String message = scanner.nextLine();
            if ("exit".equalsIgnoreCase(message)) {
                webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Client closing").thenRun(() -> {
                    System.out.println("WebSocket closed.");
                    System.exit(0);
                });
                break;
            }
            webSocket.sendText("42[\"joinGame\",\"bombparty\",\"PEGE\",\"" + message + "\"]", true);
        }
    }

    private static class WebSocketListener implements WebSocket.Listener {
        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("Connected to WebSocket server.");
            WebSocket.Listener.super.onOpen(webSocket);
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            String receivedMessage = data.toString();
            System.out.println("Message received from server: " + receivedMessage);
            if (receivedMessage.equals("2")) {
            	System.out.println("received");
                webSocket.sendText("3", true);
            }
            if (receivedMessage.substring(0,1).equals("0")) {
            	System.out.println("received");
                webSocket.sendText("40", true);
            }

            return WebSocket.Listener.super.onText(webSocket, data, last);
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("Disconnected from WebSocket server. Reason: " + reason);
            return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            System.err.println("WebSocket error: " + error.getMessage());
        }
    }
}

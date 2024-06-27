package icons; 
import java.io.IOException;
import java.net.ServerSocket;

public class AvailablePorts {

    // Method to find an available port within a specified range
    public static int findAvailablePort(int startPort, int endPort) {
        for (int port = startPort; port <= endPort; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                return port; // Port is available
            } catch (IOException e) {
                // Port is in use, try the next port
            }
        }
        throw new RuntimeException("No available ports in the range " + startPort + " to " + endPort);
    }

    public static void main(String[] args) {
        int startPort = 5000;
        int endPort = 5100;

        try {
            int availablePort = findAvailablePort(startPort, endPort);
            System.out.println("Available port found: " + availablePort);

            // Now you can use this available port to create a ServerSocket
            try (ServerSocket serverSocket = new ServerSocket(availablePort)) {
                System.out.println("Server started on port: " + availablePort);
                // Server logic here
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}

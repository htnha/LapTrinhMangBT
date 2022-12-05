import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ShapeServer {
    public final static int SERVER_PORT = 999;////10.10.10.178
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        String IP = InetAddress.getLocalHost().getHostAddress();
        try {
            System.out.println("Binding to port " + IP + ":" + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);
                    ShapeThread thread = new ShapeThread(socket);
                    thread.start();
                } catch (IOException e) {System.err.println(" Connection Error: " + e);}
            }
        } catch (IOException e1) {e1.printStackTrace();
        } finally {
            if (serverSocket != null) serverSocket.close();
        }
    }
}

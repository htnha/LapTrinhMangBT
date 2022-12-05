import java.io.*;
import java.net.Socket;

public class NumberClient {
    public final static String SERVER_IP = "169.254.235.101";//192.168.100.106
    public final static int SERVER_PORT = 100;
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataOutputStream dt = new DataOutputStream(os);
            dt.writeUTF("32 42 209 239 23 10 2");
            DataInputStream di = new DataInputStream(is);
            System.out.println(di.readUTF());
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
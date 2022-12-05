package bt;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.stream.IntStream;

public class Tong15ClientTest {
    public static int SERVER_PORT = 6868;
    public static void main(String[]args) {
        while (true) {
            try {
                System.out.println("Enter IP: ");
                InputStreamReader isr = new InputStreamReader(System.in); // Nhập
                BufferedReader br = new BufferedReader(isr); // một chuỗi
                String ip = br.readLine();
                int num1 = new Random().nextInt(1000);
                int num2 = new Random().nextInt(100000);
                Socket socket = null;
                try {
                    System.out.println("Send request to: " + ip + ":" + SERVER_PORT +" ...");
                    socket = new Socket(ip, SERVER_PORT); // Connect to server
                    socket.setSoTimeout(30000);
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    DataOutputStream dt = new DataOutputStream(os);
                    dt.writeUTF(num1 + " " + num2);
                    System.out.println(num1 + " " + num2);
                    DataInputStream di = new DataInputStream(is);
                    System.out.println(di.readUTF());
                } catch (IOException ie) {
                    System.out.println("Can't connect to server >" + ie.getMessage());
                } finally {
                    if (socket != null) {
                        socket.close();
                    }
                }
                //int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(1000)).limit(10).toArray();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

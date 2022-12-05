package bt;

import java.io.*;
import java.net.Socket;
import java.util.Random;


public class Tong15Thread extends Thread{
    String ip;
    int port;
    String name;

    public Tong15Thread(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        int num1 = new Random().nextInt(1000);
        int num2 = new Random().nextInt(100000);
        Socket socket = null;
        try {
            System.out.println(name + " > send request to: " + ip + ":" + port +" ...");
            socket = new Socket(ip, port); // Connect to server
            socket.setSoTimeout(30000);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataOutputStream dt = new DataOutputStream(os);
            dt.writeUTF(num1 + " " + num2);
            dt.flush();
            System.out.println(name + " > "+ num1 + " " + num2);
            DataInputStream di = new DataInputStream(is);
            System.out.println(name + " > "+di.readUTF());
        } catch (IOException ie) {
            System.out.println("Can't connect to server >" + ie.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

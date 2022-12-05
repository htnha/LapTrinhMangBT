package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class TestMax {
    //10.10.10.178
    public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
    public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận

    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(); // Tạo DatagramSocket
            System.out.println("Client started ");
            ds.setSoTimeout(10000);
            while (true) {
                try {
                    System.out.println("Enter IP: ");
                    InputStreamReader isr = new InputStreamReader(System.in); // Nhập
                    BufferedReader br = new BufferedReader(isr); // một chuỗi
                    String ip = br.readLine();
                    InetAddress server = InetAddress.getByName(ip);
                    int[]  randomIntsArray = IntStream.generate(() -> new Random().nextInt(1000)).limit(10).toArray();
                    String theString = Arrays.toString(randomIntsArray).replace(",","").replace("[","").replace("]",""); // từ bàn phím
                    byte[] data = theString.getBytes(); // Đổi chuỗi ra mảng bytes

                    // Tạo gói tin gởi
                    DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
                    ds.send(dp); // Send gói tin sang Echo Server
                    System.out.println("Send request to:" + ip + ">" + theString);
                    // Gói tin nhận

                    DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                    ds.receive(incoming); // Chờ nhận dữ liệu từ EchoServer gởi về

                    // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                    System.out.println("Received: " + new String(incoming.getData(), 0, incoming.getLength()));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}

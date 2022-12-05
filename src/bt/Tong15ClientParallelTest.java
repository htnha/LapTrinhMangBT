package bt;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Tong15ClientParallelTest {
    public static int SERVER_PORT = 8989;
    public static void main(String[]args) {
        while (true) {
            try {
                System.out.println("Enter IP: ");
                InputStreamReader isr = new InputStreamReader(System.in); // Nhập
                BufferedReader br = new BufferedReader(isr); // một chuỗi
                String ip = br.readLine();
                for(int i = 1; i < 9; i++){
                    Tong15Thread t = new Tong15Thread(""+i, ip, SERVER_PORT);
                    t.run();
                }

                //int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(1000)).limit(10).toArray();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

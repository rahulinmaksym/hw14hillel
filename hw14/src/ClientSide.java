import java.io.*;
import java.net.Socket;

public class ClientSide {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader readerSocket;
    private static BufferedWriter writerSocket;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 8081);
                reader = new BufferedReader(new InputStreamReader(System.in));
                readerSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writerSocket = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Привітайтеся!");
                String message = reader.readLine();
                String serverMessage;
                writerSocket.write(message + "\n");
                writerSocket.flush();

                if(message.toLowerCase().contains("ы") || message.toLowerCase().contains("ъ")
                        || message.toLowerCase().contains("э") || message.toLowerCase().contains("ё")) {
                    System.out.println("Що таке паляниця? а) ягіда б) хліб");
                    message = reader.readLine();
                    writerSocket.write(message + "\n");
                    writerSocket.flush();
                    if(message.equals("хліб")) {
                        serverMessage = readerSocket.readLine();
                        System.out.println(serverMessage);
                    }
                } else {
                    serverMessage = readerSocket.readLine();
                    System.out.println(serverMessage);
                }
            } finally {
                System.out.println("Клієнт закрито!");
                clientSocket.close();
                readerSocket.close();
                writerSocket.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class ServerSide {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader reader;
    private static BufferedWriter writer;

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(8081);
                System.out.println("Сервер запущено!");
                clientSocket = server.accept();
                try {
                    reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String message = reader.readLine();
                    System.out.println("Отримали повідомлення: " + message);

                    if(message.toLowerCase().contains("ы") || message.toLowerCase().contains("ъ")
                            || message.toLowerCase().contains("э") || message.toLowerCase().contains("ё")) {
                        message = reader.readLine();
                        System.out.println("Отримали повідомлення: " + message);
                        if(message.equals("хліб")) {
                            writer.write("Поточні дата та час: " + LocalDateTime.now().toString() + ", бувай!");
                            writer.flush();
                        }
                    } else {
                        writer.write("Привіт!");
                        writer.flush();
                    }
                } finally {
                    clientSocket.close();
                    writer.close();
                    reader.close();
                }
            } finally {
                System.out.println("Сервер закрито!");
                server.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

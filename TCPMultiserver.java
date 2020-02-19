import java.net.*;
import java.io.*;


public class TCPMultiserver {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = null;
        boolean listen = true;

        try {
            serverSocket = new ServerSocket(5230);
        } catch(IOException error){
            System.err.println("Could not listen port: 5230");
            System.exit(-1);
        }

        while(listen){
            new TCPMultiThread(serverSocket.accept()).start();
        }

        serverSocket.close();

    }
}
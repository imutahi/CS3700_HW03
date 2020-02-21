import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TCPMultiThread extends Thread {
    private Socket clientSocket = null;
    private StringBuilder requestStrings = new StringBuilder();

    public TCPMultiThread(Socket socket) {
        super("TCPMultiServerThread");
        clientSocket = socket;
    }

    public void run() {

        try {
            PrintWriter cSocketOut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader cSocketIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String fromClient;
            String requestLine = null;

            while ((fromClient = cSocketIn.readLine()) != null) {
                while (!(fromClient.equals(""))) {
                    if (requestLine == null) {
                        requestLine = fromClient;
                    }

                    System.out.println(fromClient);
                    fromClient = cSocketIn.readLine();
                    requestStrings.append(fromClient);
                    if (fromClient == null) {
                        break;
                    }
                }

                if (fromClient == null) {
                    break;
                }


                System.out.println("");

                Request request = new Request(requestStrings.toString());
                Response response = new Response(request.getType(),request.getResource());

                cSocketOut.println(response);

                requestLine = null;
                requestStrings.setLength(0);
            }

            cSocketOut.close();
            cSocketIn.close();
            clientSocket.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

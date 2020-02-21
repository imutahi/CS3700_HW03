import java.io.*;
import java.util.Date;

public class Response {
    private String filePath;
    private String body;
    private int responseCode;
    
    public Response(String responseText) {
    }

    public Response(String method, String path) {
        setFilePath(path);
        setResponseCode(method);
        if(this.responseCode == 200){
            try{
                this.body = readFile(this.filePath);
            } catch(IOException exception) {
                this.responseCode = 400;
            }
        }
    }

    public String getBody() {
        return this.body;
    }

    public String toString() {
        Date d = new Date();

        if(this.responseCode == 200){
            String responseText = new String();
            responseText += "200 OK\r\n";
            responseText += "Date: " + d.toString() + "\r\n";
            responseText += "Server: Apache/2.0.52 (CentOS)\r\n";
            responseText += "\r\n";
            responseText += this.body;
            responseText += "\r\n\r\n\r\n\r\n";
            return responseText;
        } else if(this.responseCode == 400) {
            String responseText = new String();
            responseText += "400 Bad Request\r\n";
            responseText += "Date: " + d.toString() + "\r\n";
            responseText += "Server: Apache/2.0.52 (CentOS)\r\n";
            responseText += "\r\n";
            return responseText;
        } else if(this.responseCode == 404) {
            String responseText = new String();
            responseText += "404 Not Found\r\n";
            responseText += "Date: " + d.toString() + "\r\n";
            responseText += "Server: Apache/2.0.52 (CentOS)\r\n";
            responseText += "\r\n";
            return responseText;
        } else {
            return null;
        }
    }

    public void setFilePath(String filePath) {
    	this.filePath = "." + filePath;
    }

    private void setResponseCode(String method) {
        File f = new File(this.filePath);

        if (method.equals("GET") && (f.isFile())) {
            this.responseCode = 200;
        } else if ((method.equals("GET")) && !f.isFile()) {
            this.responseCode = 404;
        } else {
            this.responseCode = 400;
        }

    }

    public static int getResponseCode() {
        return this.responseCode;
    }

    public void writeBodyToDisk() {

    }

    //Method found on Stack Overflow (author is Mr.D): https://stackoverflow.com/questions/16027229/reading-from-a-text-file-and-storing-in-a-string
    private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }


}

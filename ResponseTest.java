import java.io.*;
import java.util.Date;

public class ResponseTest {
    public static void main(String[] args) {
        return_200_OK();
        return_400_Bad_Request();
        return_404_Not_Found();
        returned_200_OK();
        returned_400_Bad_Request();
        returned_404_Not_Found();
    }

    private static void return_200_OK() {
        String htmFile = null;

        try {
            htmFile = readFile("3700.htm");
        } catch(IOException exception) {
            System.out.println("Couldn't read HTM file!");
            htmFile = "";
        }

        Response response = new Response("GET", "/3700.htm");
        Date d = new Date();
        String expectedText = "200 OK\r\n" + 
                              "Date: " + d.toString() + "\r\n" +
                              "Server: Apache/2.0.52 (CentOS)\r\n" +
                              "\r\n" +
                              htmFile + 
                              "\r\n" +
                              "\r\n" +
                              "\r\n" +
                              "\r\n";

        testIfEqual("To String",expectedText,response.toString());
    }

    private static void returned_200_OK() {

        String htmFile = null;
        try {
            htmFile = readFile("3700.htm");
        } catch(IOException exception) {
            System.out.println("Couldn't read HTM file!");
            htmFile = "";
        }

        Response expectedResponse = new Response("GET", "/3700.htm");
        String expectedResponseText = expectedResponse.toString();

        Response response = new Response(expectedResponseText);
        response.setFilePath("/3700.htm2");
        testIfEqual("From String - Response Code",200,response.getResponseCode());
        testIfEqual("From String - Body",htmFile,response.getBody());

        response.writeBodyToDisk();
        String htmFile2 = null;
        try {
            htmFile = readFile("3700.htm2");
        } catch(IOException exception) {
            System.out.println("Couldn't read HTM file!");
            htmFile = "";
        }
        testIfEqual("From String - To Disk",htmFile,htmFile2);
    }

    private static void return_400_Bad_Request() {
        Response response = new Response("POST", "/3700.htm");
        Date d = new Date();
        String expectedText = "400 Bad Request\r\n" + 
                              "Date: " + d.toString() + "\r\n" +
                              "Server: Apache/2.0.52 (CentOS)\r\n" +
                              "\r\n";

        testIfEqual("To String",expectedText,response.toString());
    }
    
    private static void returned_400_Bad_Request() {
        Response expectedResponse = new Response("POST", "/3700.htm");
        String expectedResponseText = expectedResponse.toString();
        Response response = new Response(expectedResponseText);
        testIfEqual("From String - Response Code",400,response.getResponseCode());
    }
    
    private static void return_404_Not_Found() {
        Response response = new Response("GET", "/3800.htm");
        Date d = new Date();
        String expectedText = "404 Not Found\r\n" + 
                              "Date: " + d.toString() + "\r\n" +
                              "Server: Apache/2.0.52 (CentOS)\r\n" +
                              "\r\n";

        testIfEqual("To String",expectedText,response.toString());
    }

    private static void returned_404_Not_Found() {
        Response expectedResponse = new Response("GET", "/3800.htm");
        String expectedResponseText = expectedResponse.toString();
        Response response = new Response(expectedResponseText);
        testIfEqual("From String - Response Code",404,response.getResponseCode());
    }
    
    
    private static void testIfEqual(String title, String expected, String result) {
        if(expected.equals(result)) {
            System.out.println(title + " passed: " + result + " equals " + expected + ".");
        } else {
            System.out.println(title + " failed: " + result + " does not equal " + expected + ".");
        }
    }

    private static void testIfEqual(String title, int expected, int result) {
        if(expected == result) {
            System.out.println(title + " passed: " + result + " equals " + expected + ".");
        } else {
            System.out.println(title + " failed: " + result + " does not equal " + expected + ".");
        }
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

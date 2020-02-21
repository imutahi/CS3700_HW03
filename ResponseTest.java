import java.io.*;

public class ResponseTest {
    public static void main(String[] args) {
        String htmFile = null;
        try {
            htmFile = readFile("3700.htm");
        } catch(IOException exception) {
            System.out.println("Couldn't read HTM file!");
            htmFile = "";
        }

        Response response = new Response("GET", "/3700.htm");
        String expectedText = "200 OK\r\n" + 
                              "\r\n" +
                              htmFile + 
                              "\r\n" +
                              "\r\n" +
                              "\r\n" +
                              "\r\n";

        testIfEqual("To String",expectedText,response.toString());
    }

    private static void testIfEqual(String title, String expected, String result) {
        if(expected.equals(result)) {
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

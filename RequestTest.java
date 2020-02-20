public class RequestTest {

    public static void main(String[] args) {
        String requestText = "GET /CS3700.htm HTTP/1.1\r\n"  +
                            "Host: 3700a.msudenver.edu\r\n" +
                            "User-Agent: Mozzilla/4.0\r\n"  +
                            "\r\n";
        Request request = new Request(requestText);
        testIfEqual("Request Type","GET",request.getType());
        testIfEqual("Requested Resource","/CS3700.htm",request.getResource());
        testIfEqual("HTTP Version","HTTP/1.1",request.getHTTPVersion());
        testIfEqual("Host","3700a.msudenver.edu",request.getHost());
        testIfEqual("User-Agent","Mozzilla/4.0",request.getUserAgent());
    }

    private void testIfEqual(String title, String expected, String result) {
        if(expected==result) {
            System.out.println(title + " passed: " + result + " equals " + expected + ".");
        } else {
            System.out.println(title + " failed: " + result + " does not equal " + expected + ".");
        }
    }

}

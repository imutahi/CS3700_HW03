public class Request {

    private String type;
    private String resource;
    private String HTTPVersion;
    private String host;
    private String userAgent;

    public Request(String requestText) {
        String[] requestLines = requestText.split("\r\n",4);
        parseFirstRequestLine(requestLines[0]);
        parseOtherRequestLine(requestLines[1]);
        parseOtherRequestLine(requestLines[2]);
    }

    public String getType() {
        return this.type;
    }

    public String getResource() {
        return this.resource;
    }

    public String getHTTPVersion() {
        return this.HTTPVersion;
    }

    public String getHost() {
        return this.host;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String toString() {
        String firstLine = this.type + " " + this.resource + " " + this.HTTPVersion + "\r\n";
        String secondLine = "Host: " + this.host + "\r\n";
        String thirdLine = "User-Agent: " + this.userAgent + "\r\n";
        String fourthLine = "\r\n";

        String responseString = "";
        responseString += firstLine;
        responseString += secondLine;
        responseString += thirdLine;
        responseString += fourthLine;

        return responseString;
    }

    private void parseFirstRequestLine(String headerLine) {
        String[] values = headerLine.strip().split(" ",3);
        this.type = values[0];
        this.resource = values[1];
        this.HTTPVersion = values[2];
    }

    private void parseOtherRequestLine(String headerLine) {
        String[] values = headerLine.strip().split(" ",2);

        if (values[0].equals("Host:")) {
            this.host = values[1];
        }

        if (values[0].equals("User-Agent:")) {
            this.userAgent = values[1];
        }

    }

}

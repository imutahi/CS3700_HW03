public class Request {

    private String type;
    private String resource;
    private String HTTPVersion;
    private String host;
    private String userAgent;

    public Request(String requestText) {
        this.type = null;
        this.resource = null;
        this.HTTPVersion = null;
        this.host = null;
        this.userAgent = null;

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

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

        String[] requestLines = requestText.split("\r\n",5);
        parseHeaderLine1(requestLines[0]);
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

    private void parseHeaderLine1(String headerLine) {
        String[] values = headerLine.strip().split(" ",3);
        this.type = values[0];
        this.resource = values[1];
        this.HTTPVersion = values[2];
    }

}

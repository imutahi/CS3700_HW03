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

}

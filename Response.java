public class Response {
    private String filePath;
    private String body; 
    public Response() {
    }

    public String getBody() {
        return this.body;
    }

    public static void setFilePath(String filePath) {
    	this.filePath = String filepath; 
        this.body = readFile(filepath);
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

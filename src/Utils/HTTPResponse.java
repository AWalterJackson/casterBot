package Utils;

import org.json.JSONObject;

public class HTTPResponse {
    public enum  Protocol { HTTP, HTTPS }

    public int responseCode;
    public Protocol protocol;
    public String content;

    public HTTPResponse(int code, Protocol protocol, String content) {
        this.responseCode = code;
        this.protocol = protocol;
        this.content = content;
    }
}

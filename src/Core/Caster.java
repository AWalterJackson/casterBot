package Core;

import Utils.HTTPResponse;
import Utils.HttpsHandler;
import org.json.JSONObject;

public class Caster {
    private String api_key;
    private String api_root = "https://api.telegram.org/bot";

    Caster(String key){
        this.api_key = key;
    }

    public HTTPResponse getDetails() {
        return HttpsHandler.httpsget(api_root + api_key + "/getMe");
    }

    //public HTTPResponse broadcast() { }

}

package Core;

import Utils.HttpsHandler;
import org.json.JSONArray;

public class Caller {

    private int threshold;
    private String url_root;

    Caller(String url, int threshold) {
        this.url_root = url;
        this.threshold = threshold;
    }

    public JSONArray callSearchAPI(String searchData){
        return new JSONArray(HttpsHandler.httpsget(this.url_root + "post/index.json?limit=5").content);
    }
}

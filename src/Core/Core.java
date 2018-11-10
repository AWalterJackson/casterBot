package Core;

import Utils.E621Post;
import Utils.HTTPResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Utils.NBotlogger;

public class Core {

    private static final String CONFIG_FILE = "config.json";
    private static final String DATA_FILE = "backsack.json";

    public static void main(String[] args) {

        JSONObject config;
        String api_key;
        String topic;
        String search_string;
        String url_root;

        int frequency;
        int threshold;

        /**
         *  Init the bot here, reading the config file for the global values
         *  */
        try {
            BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            config = new JSONObject(sb.toString());
            br.close();

            api_key = config.getString("api_token");
            topic = config.getString("topic");
            search_string = config.getString("search_string");
            url_root = config.getString("url_root");
            frequency = config.getInt("frequency");
            threshold = config.getInt("threshold");

        } catch (FileNotFoundException e) {
            NBotlogger.log("CORE", "Config file not found");
            return;
        } catch (IOException e) {
            NBotlogger.log("CORE", "Error reading config file");
            return;
        }
        NBotlogger.log("CORE", "Finished loading config.");

        /**
         * Init the connection to the Telegram API
         */
        Caster telegram = new Caster(api_key);
        HTTPResponse rep = telegram.getDetails();
        NBotlogger.log("CORE", "Initialising Telegram");
        if(rep.responseCode == 200) {
            NBotlogger.log("CORE", "Telegram API ready");
        } else {
            NBotlogger.log("CORE", "Telegram API returned: " + rep.responseCode);
        }

        /**
         * Init data here, attempt to read the stored file, and do a full sweep if it doesn't exist.
         */
        Caller api = new Caller(url_root, threshold);
        ArrayList<E621Post> callData = new ArrayList<E621Post>();
        int currentPage = 1;

        JSONArray dataIn = api.callSearchAPI(search_string, 320, currentPage, 2000000);
        while(dataIn.length() > 0) {
            for(int i = 0; i < dataIn.length(); i++) {
                JSONObject currentPost = dataIn.getJSONObject(i);
                E621Post post = new E621Post();
                post.id = currentPost.getInt("id");
                post.file_size = currentPost.getInt("file_size");
                post.setArtists(currentPost.getJSONArray("artist"));
                try {
                    post.source = currentPost.getString("source");
                } catch(Exception e) {
                    post.source = "";
                }
                post.extension = currentPost.getString("file_ext");
                post.sample_url = currentPost.getString("sample_url");
                post.file_url = currentPost.getString("file_url");
                post.score = currentPost.getInt("score");
                post.favs = currentPost.getInt("fav_count");
                callData.add(post);
            }
            currentPage++;
            System.out.println(currentPage);
            dataIn = api.callSearchAPI(search_string, 320, currentPage, callData.get(callData.size()-1).id);
        }
        NBotlogger.log("CORE", "Called " + currentPage + " pages.");

    }
}

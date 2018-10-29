package Core;

import Utils.HTTPResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        NBotlogger.log();
        System.out.println(rep.responseCode);
        System.out.println(rep.protocol);
        System.out.println(rep.content);
        if(rep.responseCode == 200) {
            NBotlogger.log("CORE", "Telegram API ready");
        } else {
            NBotlogger.log("CORE", "Telegram API returned: " + rep.responseCode);
        }
    }
}

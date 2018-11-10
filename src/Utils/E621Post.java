package Utils;

import org.json.JSONArray;

public class E621Post {
    public String file_url;
    public String sample_url;
    public String[] artist;
    public String source;
    public int id;
    public int file_size;
    public String extension;
    public int score;
    public int favs;


    public E621Post() {
        this.file_url = "";
        this.sample_url = "";
        this.source = "";
        this.id = -1;
        this.file_size = -1;
        this.score = 0;
        this.favs = -1;
        this.extension = "";
    }

    public void setArtists(JSONArray artists){
        this.artist = new String[artists.length()];
        for(int i=0; i < artists.length(); i++){
            this.artist[i] = artists.getString(i);
        }
    }
}

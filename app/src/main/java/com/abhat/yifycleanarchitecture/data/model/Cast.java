package com.abhat.yifycleanarchitecture.data.model;

/**
 * Created by Anirudh Uppunda on 20/9/17.
 */

public class Cast {
    private String name;
    private String character_name;
    private String url_small_image;
    private String imdb_code;

    public String getImdb_code() {
        return imdb_code;
    }

    public void setImdb_code(String imdb_code) {
        this.imdb_code = imdb_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public String getUrl_small_image() {
        return url_small_image;
    }

    public void setUrl_small_image(String url_small_image) {
        this.url_small_image = url_small_image;
    }
}

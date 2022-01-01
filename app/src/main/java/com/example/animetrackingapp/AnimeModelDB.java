package com.example.animetrackingapp;

public class AnimeModelDB {
    public static final String TABLE_NAME = "anime";

    public static final String FIELD_ANIME_ID = "anime_id";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_EPISODES = "episodes";
    public static final String FIELD_SCORE = "score";
    public static final String FIELD_RATED = "rated";
    public static final String FIELD_SYNOPSIS = "synopsis";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_URL = "url";
    public static final String FIELD_LIST_TYPE = "list_type";

    private int animeId;
    private String title, type, episodes, score, rated, synopsis, image, url, list_type;

    public AnimeModelDB(int animeId, String title, String type, String episodes, String score, String rated, String synopsis, String image, String url, String list_type) {
        this.animeId = animeId;
        this.title = title;
        this.type = type;
        this.episodes = episodes;
        this.score = score;
        this.rated = rated;
        this.synopsis = synopsis;
        this.image = image;
        this.url = url;
        this.list_type = list_type;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    @Override
    public String toString() {
        return "AnimeModelDB{" +
                "animeId=" + animeId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", episodes='" + episodes + '\'' +
                ", score='" + score + '\'' +
                ", rated='" + rated + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", list_type='" + list_type + '\'' +
                '}';
    }
}

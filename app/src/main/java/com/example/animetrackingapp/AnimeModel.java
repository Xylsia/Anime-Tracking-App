package com.example.animetrackingapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class AnimeModel {
    private String title, type, episodes, score, synopsis, image_url, url, rated;

    public AnimeModel(){}

    public AnimeModel(String title, String type, String episodes, String score, String synopsis, String image_url, String url, String rated) {
        this.title = title;
        this.type = type;
        this.episodes = episodes;
        this.score = score;
        this.synopsis = synopsis;
        this.image_url = image_url;
        this.url = url;
        this.rated = rated;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public static AnimeModel parseJSONObject(JSONObject jsonObject){
        AnimeModel animeModel = new AnimeModel();

        try{
            if (jsonObject.has("title")) {
                animeModel.setTitle(jsonObject.getString("title"));
            }

            if (jsonObject.has("type")) {
                animeModel.setType(jsonObject.getString("type"));
            }

            if (jsonObject.has("episodes")) {
                animeModel.setEpisodes(jsonObject.getString("episodes"));
            }

            if (jsonObject.has("score")) {
                animeModel.setScore(jsonObject.getString("score"));
            }

            if (jsonObject.has("synopsis")) {
                animeModel.setSynopsis(jsonObject.getString("synopsis"));
            }

            if (jsonObject.has("image_url")) {
                animeModel.setImage_url(jsonObject.getString("image_url"));
            }

            if (jsonObject.has("url")) {
                animeModel.setUrl(jsonObject.getString("url"));
            }

            if (jsonObject.has("rated")) {
                animeModel.setRated(jsonObject.getString("rated"));
            }
        }
        catch(Exception e){}

        return animeModel;
    }

    public static LinkedList<AnimeModel> parseJSONArray(JSONArray jsonArray){
        LinkedList<AnimeModel> list = new LinkedList<>();

        try{
            for(int i = 0; i < jsonArray.length(); i++){
                AnimeModel animeModel = parseJSONObject(jsonArray.getJSONObject(i));
                list.add(animeModel);
            }
        }
        catch(Exception e){}

        return list;
    }
}

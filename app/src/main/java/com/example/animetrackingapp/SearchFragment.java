package com.example.animetrackingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.LinkedList;

public class SearchFragment extends Fragment implements View.OnClickListener{

    public SearchFragment(){}

    View v;
    ConstraintLayout item;
    SearchView svSearch;
    LinearLayout mainScrollView;
//    String url = "https://api.jikan.moe/v3/search/anime?q=" + ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, container, false);

        svSearch = v.findViewById(R.id.svSearch);

        SearchBar();
        return v;

    }


    @SuppressLint("HandlerLeak")
    private void InitAnime(View v, String url){

        Api.getJSON(url, new ReadDataHandler(){
            @Override
            public void handleMessage( Message msg) {
                String answer =getJson();

                try{
                    JSONObject jsonObject = new JSONObject(answer);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    LinkedList<AnimeModel> anime = AnimeModel.parseJSONArray(jsonArray);

                    mainScrollView = v.findViewById(R.id.mainScrollView);
                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    for(AnimeModel a : anime){

                        item = (ConstraintLayout) inflater.inflate(R.layout.one_anime, null);
                        item.setOnClickListener(SearchFragment.this);

                        TextView textTitle = item.findViewById(R.id.textTitle);
                        textTitle.setText(a.getTitle());
                        TextView textEpisode = item.findViewById(R.id.textEpisode);
                        textEpisode.setText(a.getEpisodes());
                        TextView textScore = item.findViewById(R.id.textScore);
                        textScore.setText(a.getScore());
                        TextView textType = item.findViewById(R.id.textType);
                        textType.setText(a.getType());
                        TextView textRating = item.findViewById(R.id.textRating);
                        textRating.setText(a.getRated());
                        TextView textSynopsis = item.findViewById(R.id.textSynopsis);
                        textSynopsis.setText(a.getSynopsis());
                        TextView textUrl = item.findViewById(R.id.textUrl);
                        textUrl.setText(a.getUrl());
                        TextView textImage = item.findViewById(R.id.textImage);
                        textImage.setText(a.getImage_url());

                        ImageView imageView = item.findViewById(R.id.animeImg);
                        Picasso.get().load(a.getImage_url()).into(imageView);

                        mainScrollView.addView(item);
                    }
                }
                catch(Exception e){ }
            }
        });
    }

    private void SearchBar(){

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String encodedQuery = URLEncoder.encode(query);

                String url = "https://api.jikan.moe/v3/search/anime?q=" + encodedQuery;

                mainScrollView = v.findViewById(R.id.mainScrollView);
                int children = mainScrollView.getChildCount();
                if (children > 1) {
                    mainScrollView.removeViews(1, children - 1);
                }

                System.out.println(url);

                svSearch.clearFocus();
                InitAnime(v, url);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.clickOne:
                TextView textTitle = v.findViewById(R.id.textTitle);
                TextView textEpisode = v.findViewById(R.id.textEpisode);
                TextView textScore = v.findViewById(R.id.textScore);
                TextView textType = v.findViewById(R.id.textType);
                TextView textRating = v.findViewById(R.id.textRating);
                TextView textSynopsis = v.findViewById(R.id.textSynopsis);
                TextView textUrl = v.findViewById(R.id.textUrl);

                TextView textImage = v.findViewById(R.id.textImage);

                String title = textTitle.getText().toString();
                String episode = textEpisode.getText().toString();
                String score = textScore.getText().toString();
                String type = textType.getText().toString();
                String rating = textRating.getText().toString();
                String synopsis = textSynopsis.getText().toString();
                String url = textUrl.getText().toString();

                String image = textImage.getText().toString();

                Intent intent = new Intent(getActivity(), AnimeDescription.class);
                Bundle extras = new Bundle();

                extras.putString("title", title);
                extras.putString("episode", episode);
                extras.putString("score", score);
                extras.putString("type", type);
                extras.putString("rating", rating);
                extras.putString("synopsis", synopsis);
                extras.putString("url", url);

                extras.putString("image", image);

                intent.putExtras(extras);
                startActivity(intent);
                break;
        }
    }
}

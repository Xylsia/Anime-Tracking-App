package com.example.animetrackingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlanToWatchFragment extends Fragment  implements View.OnClickListener{

    View v;
    ConstraintLayout anime;

    public PlanToWatchFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_plan_to_watch, container, false);

        initAnime();

        return v;
    }

    private void initAnime() {

        DataBase db = new DataBase(getContext());
        List<AnimeModelDB> animes = db.getAllAnime("planToWatch");

        LinearLayout mainScrollView = v.findViewById(R.id.mainScrollView);
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        for (AnimeModelDB a : animes) {
            anime = (ConstraintLayout) inflater.inflate(R.layout.one_anime, null);
            anime.setOnClickListener(this);

            int aId = a.getAnimeId();
            TextView textTitle = anime.findViewById(R.id.textTitle);
            textTitle.setText(a.getTitle());
            TextView textEpisode = anime.findViewById(R.id.textEpisode);
            textEpisode.setText(a.getEpisodes());
            TextView textScore = anime.findViewById(R.id.textScore);
            textScore.setText(a.getScore());
            TextView textType = anime.findViewById(R.id.textType);
            textType.setText(a.getType());
            TextView textRating = anime.findViewById(R.id.textRating);
            textRating.setText(a.getRated());
            TextView textSynopsis = anime.findViewById(R.id.textSynopsis);
            textSynopsis.setText(a.getSynopsis());
            TextView textUrl = anime.findViewById(R.id.textUrl);
            textUrl.setText(a.getUrl());
            TextView textImage = anime.findViewById(R.id.textImage);
            textImage.setText(a.getImage());

            ImageView image = anime.findViewById(R.id.animeImg);
            Picasso.get().load(a.getImage()).into(image);

            Button btnRemove = new Button(getContext());
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    160,
                    65
            );
            btnRemove.setLayoutParams(params);
            btnRemove.setBackgroundResource(R.color.purple_500);
            btnRemove.setTextColor(getResources().getColor(R.color.white));
            btnRemove.setTextSize(10);
            btnRemove.setText("Remove");
            btnRemove.setId(aId);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteAnime(aId);
                    btnRemove.setEnabled(false);
                    btnRemove.setText("Removed");

                    Toast.makeText(getContext(), "Removed from list.", Toast.LENGTH_SHORT).show();
                }
            });
            anime.addView(btnRemove);

            mainScrollView.addView(anime);
        }

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

package com.example.animetrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AnimeDescription extends AppCompatActivity implements View.OnClickListener{

    String title, episode, score, type, rating, synopsis, url, image;
    Button buttonPlanToWatch, buttonWatching, buttonFinished, buttonWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_description);

        initComponents();
    }

    private void initComponents(){
        buttonPlanToWatch = findViewById(R.id.buttonPlanToWatch);
        buttonPlanToWatch.setOnClickListener(this);
        buttonWatching = findViewById(R.id.buttonWatching);
        buttonWatching.setOnClickListener(this);
        buttonFinished = findViewById(R.id.buttonFinished);
        buttonFinished.setOnClickListener(this);
        buttonWeb = findViewById(R.id.buttonWeb);
        buttonWeb.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        title = extras.getString("title");
        episode = extras.getString("episode");
        score = extras.getString("score");
        type = extras.getString("type");
        rating = extras.getString("rating");
        synopsis = extras.getString("synopsis");
        url = extras.getString("url");
        image = extras.getString("image");

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        TextView txtEpisode = findViewById(R.id.txtEpisodes);
        txtEpisode.setText(episode);
        TextView txtScore = findViewById(R.id.txtScore);
        txtScore.setText(score);
        TextView txtType = findViewById(R.id.txtType);
        txtType.setText(type);
        TextView txtRating = findViewById(R.id.txtRated);
        txtRating.setText(rating);
        TextView txtSynopsis = findViewById(R.id.txtSynopsis);
        txtSynopsis.setText(synopsis);
        TextView txtUrl = findViewById(R.id.txtUrl);
        txtUrl.setText(url);
        ImageView imageView = findViewById(R.id.imageView);
        Picasso.get().load(image).into(imageView);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonWeb:

                Intent intent = new Intent(this, AnimeWebView.class);
                Bundle extras = new Bundle();

                extras.putString("url", url);

                intent.putExtras(extras);
                startActivity(intent);
                break;

            case R.id.buttonWatching:
                DataBase db = new DataBase(this);
                List<AnimeModelDB> anime = db.getAllAnime("watching");

                ArrayList<String> titles1 = new ArrayList<>();
                for (AnimeModelDB a : anime) {
                    String titleFromDb = a.getTitle();
                    String listTypeDB = a.getList_type();

                    titles1.add(titleFromDb);
                    titles1.add(listTypeDB);
                }

                if (titles1.contains(title) && titles1.contains("watching")){
                    Toast.makeText(this, "This anime is already in a list.", Toast.LENGTH_SHORT).show();
                }else{
                    db.addAnime(title, type, episode, score, rating, synopsis, image, url, "watching");
                    Toast.makeText(this, "Added to list.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonPlanToWatch:
                db = new DataBase(this);
                anime = db.getAllAnime("planToWatch");

                ArrayList<String> titles2 = new ArrayList<>();
                for (AnimeModelDB a : anime) {
                    String titleFromDb = a.getTitle();
                    String listTypeDB = a.getList_type();

                    titles2.add(titleFromDb);
                    titles2.add(listTypeDB);
                }

                if (titles2.contains(title) && titles2.contains("planToWatch")){
                    Toast.makeText(this, "This anime is already in a list.", Toast.LENGTH_SHORT).show();
                }else{
                    db.addAnime(title, type, episode, score, rating, synopsis, image, url, "planToWatch");
                    Toast.makeText(this, "Added to list.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonFinished:
                db = new DataBase(this);
                anime = db.getAllAnime("finished");

                ArrayList<String> titles3 = new ArrayList<>();
                for (AnimeModelDB a : anime) {
                    String titleFromDb = a.getTitle();
                    String listTypeDB = a.getList_type();

                    titles3.add(titleFromDb);
                    titles3.add(listTypeDB);
                }

                if (titles3.contains(title) && titles3.contains("finished")){
                    Toast.makeText(this, "This anime is already in a list.", Toast.LENGTH_SHORT).show();
                }else{
                    db.addAnime(title, type, episode, score, rating, synopsis, image, url, "finished");
                    Toast.makeText(this, "Added to list.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
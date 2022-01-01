package com.example.animetrackingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_FILE_NAME = "my_list";
    public DataBase(Context context){super(context, DATABASE_FILE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format(
                "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                    AnimeModelDB.TABLE_NAME, AnimeModelDB.FIELD_ANIME_ID, AnimeModelDB.FIELD_TITLE, AnimeModelDB.FIELD_TYPE, AnimeModelDB.FIELD_EPISODES, AnimeModelDB.FIELD_SCORE,
                    AnimeModelDB.FIELD_RATED, AnimeModelDB.FIELD_SYNOPSIS, AnimeModelDB.FIELD_IMAGE, AnimeModelDB.FIELD_URL, AnimeModelDB.FIELD_LIST_TYPE
         );

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", AnimeModelDB.TABLE_NAME));
        onCreate(db);
    }

    public void addAnime(String title, String type, String episodes, String score, String rated, String synopsis, String image, String url, String list_type){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(AnimeModelDB.FIELD_TITLE, title);
        cv.put(AnimeModelDB.FIELD_TYPE, type);
        cv.put(AnimeModelDB.FIELD_EPISODES, episodes);
        cv.put(AnimeModelDB.FIELD_SCORE, score);
        cv.put(AnimeModelDB.FIELD_RATED, rated);
        cv.put(AnimeModelDB.FIELD_SYNOPSIS, synopsis);
        cv.put(AnimeModelDB.FIELD_IMAGE, image);
        cv.put(AnimeModelDB.FIELD_URL, url);
        cv.put(AnimeModelDB.FIELD_LIST_TYPE, list_type);

        db.insert(AnimeModelDB.TABLE_NAME, null, cv);
    }

    public void editAnime(int animeId, String title, String type, String episodes, String score, String rated, String synopsis, String image, String url, String list_type){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(AnimeModelDB.FIELD_TITLE, title);
        cv.put(AnimeModelDB.FIELD_TYPE, type);
        cv.put(AnimeModelDB.FIELD_EPISODES, episodes);
        cv.put(AnimeModelDB.FIELD_SCORE, score);
        cv.put(AnimeModelDB.FIELD_RATED, rated);
        cv.put(AnimeModelDB.FIELD_SYNOPSIS, synopsis);
        cv.put(AnimeModelDB.FIELD_IMAGE, image);
        cv.put(AnimeModelDB.FIELD_URL, url);
        cv.put(AnimeModelDB.FIELD_LIST_TYPE, list_type);

        db.update(AnimeModelDB.TABLE_NAME, cv, AnimeModelDB.FIELD_ANIME_ID + " = ?", new String[] {String.valueOf(animeId)});
    }

    public int deleteAnime(int animeId){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(AnimeModelDB.TABLE_NAME,AnimeModelDB.FIELD_ANIME_ID + " = ?", new String[] {String.valueOf(animeId)});
    }

    public List<AnimeModelDB> getAllAnime(String list_type){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s  WHERE %s = ?", AnimeModelDB.TABLE_NAME, AnimeModelDB.FIELD_LIST_TYPE);
        Cursor result = db.rawQuery(query,  new String[] {String.valueOf(list_type)});
        result.moveToFirst();

        List<AnimeModelDB> list = new ArrayList<>(result.getCount());

        while (!result.isAfterLast()){
            int animeId = result.getInt(result.getColumnIndex(AnimeModelDB.FIELD_ANIME_ID));
            String title = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_TITLE));
            String type = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_TYPE));
            String episodes = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_EPISODES));
            String score = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_SCORE));
            String rated = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_RATED));
            String synopsis = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_SYNOPSIS));
            String image = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_IMAGE));
            String url = result.getString(result.getColumnIndex(AnimeModelDB.FIELD_URL));

            AnimeModelDB post = new AnimeModelDB(animeId, title, type, episodes, score, rated, synopsis, image, url, list_type);

            list.add(post);
            result.moveToNext();
        }
        return list;
    }
}

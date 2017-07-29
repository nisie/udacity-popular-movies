package com.nisie.popularmovies.movielist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favoritemovies.db";
    private static final int DATABASE_VERSION = 2;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVORITE_MOVIES_TABLE =
                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MovieContract.MovieEntry.COLUMN_ID + " INTEGER NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_TITLE + " STRING NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_IMAGE + " STRING NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_RATING + " FLOAT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " STRING NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_SYNOPSIS + " STRING NOT NULL, " +
                        " UNIQUE (" + MovieContract.MovieEntry.COLUMN_ID + ") ON CONFLICT " +
                        "REPLACE); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

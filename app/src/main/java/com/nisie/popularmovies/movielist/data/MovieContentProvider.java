package com.nisie.popularmovies.movielist.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author by nisie on 7/29/17.
 */

public class MovieContentProvider extends ContentProvider {

    public static final int MOVIES = 101;
    public static final int MOVIES_WITH_ID = 102;

    public static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH_MOVIE, MOVIES);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH_MOVIE + "/#", MOVIES_WITH_ID);

        return uriMatcher;
    }

    private static final int DBVERSION = 1;
    MovieDbHelper dbHelper;
    private static final String DBNAME = "MovieDB";
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new MovieDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {
        db = dbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        Cursor cursor;
        switch (match) {
            case MOVIES:
                cursor = db.query(MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case MOVIES:
                long id = db.insert(
                        MovieContract.MovieEntry.TABLE_NAME,
                        null,
                        contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MovieContract.MovieEntry
                            .CONTENT_URI, id);
                } else
                    throw new SQLException("Failed to Insert row into " + uri);
                break;
            default:
                throw new IllegalArgumentException();
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[]
            selectionArgs) {
        db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        int deleteCount;
        switch (match) {
            case MOVIES:
                deleteCount = db.delete(MovieContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException();
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

package com.shivam.wish;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GenreActivity extends AppCompatActivity {
    private List<Genre> genreList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GenreAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        recyclerView = (RecyclerView) findViewById(R.id.genre_list);
        LinearLayoutManager gLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(gLayoutManager);
        gAdapter = new GenreAdapter(genreList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gAdapter);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                Log.d("PERMISSIONS", "Requesting Permissions.");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            Log.d("PERMISSIONS", "Permissions already granted.");
            loadAudiofiles();
        }
    }

    public void loadAudiofiles() {
        ContentResolver ContentResolver = getContentResolver();

        int index;
        long genreId;
        Uri uri;
        Cursor genrecursor;
        Cursor tempcursor;
        String[] proj1 = {MediaStore.Audio.Genres.NAME, MediaStore.Audio.Genres._ID};
        String[] proj2 = {MediaStore.Audio.Media.DISPLAY_NAME};

        genrecursor = ContentResolver.query(MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI, proj1, null, null, null);
        if (genrecursor.moveToFirst()) {
            do {
                index = genrecursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME);
                String name = genrecursor.getString(genrecursor.getColumnIndex(MediaStore.Audio.Genres.NAME));

                Log.i("Tag-Genre name", genrecursor.getString(index));
                genreList.add(new Genre(name));

                index = genrecursor.getColumnIndexOrThrow(MediaStore.Audio.Genres._ID);
                genreId = Long.parseLong(genrecursor.getString(index));
                uri = MediaStore.Audio.Genres.Members.getContentUri("external", genreId);

                tempcursor = ContentResolver.query(uri, proj2, null, null, null);

                if (tempcursor.moveToFirst()) {
                    do {
                        index = tempcursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                        Log.i("Tag-Song name", tempcursor.getString(index));
                    } while (tempcursor.moveToNext());
                }
            } while (genrecursor.moveToNext());
        }

        gAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadAudiofiles();
        }
    }
}


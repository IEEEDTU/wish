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

public class ArtistActivity extends AppCompatActivity {
    private List<Artist> artistList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArtistAdapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        recyclerView = (RecyclerView) findViewById(R.id.artist_list);
        LinearLayoutManager aLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(aLayoutManager);
        aAdapter = new ArtistAdapter(artistList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aAdapter);


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
        String[] aProjection =
                {

                        MediaStore.Audio.Artists.ARTIST
                };

        Cursor cursor = ContentResolver.query(
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                aProjection,
                null,
                null,
                MediaStore.Audio.Artists.ARTIST + " ASC");

        Log.d("ARTIST", String.valueOf(cursor.getCount()));

        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext())
            {

                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
                Log.d("ARTIST", artist);


                artistList.add(new Artist(artist));

            }
        }
        cursor.close();
        aAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadAudiofiles();
        }
    }
}


package com.shivam.wish;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Music> musicList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MusicAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.music_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MusicAdapter(musicList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


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
       Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = ContentResolver.query(uri, null, selection, null, sortOrder);

        Log.d("MUSIC", String.valueOf(cursor.getCount()));

        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext())
            {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String year = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));


                Log.d("MUSIC", title);

                musicList.add(new Music(title, album, year, artist));

            }
        }
        cursor.close();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadAudiofiles();
        }
    }

}
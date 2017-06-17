package com.shivam.wish;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class AlbumActivity extends AppCompatActivity {
    private List<Album> albumList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlbumAdapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        recyclerView = (RecyclerView) findViewById(R.id.album_list);
        LinearLayoutManager aLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(aLayoutManager);
        aAdapter = new AlbumAdapter(albumList);
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
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = ContentResolver.query(uri, null, selection, null, sortOrder);

        Log.d("MUSIC", String.valueOf(cursor.getCount()));

        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext())
            {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String year = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String path = cursor.getString(cursor.getColumnIndex(android.provider.MediaStore.Audio.Albums.ALBUM_ART));

                Log.d("MUSIC", title);

                Bitmap bm= BitmapFactory.decodeFile(path);
                ImageView image=(ImageView)findViewById(R.id.album_art);
                image.setImageBitmap(bm);
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
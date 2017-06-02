package com.shivam.wish;

/**
 * Created by Harish Gola on 17-04-2017.
 */

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {


    private List<Music> musicList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, album, artist;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            album = (TextView) view.findViewById(R.id.album);
            artist = (TextView) view.findViewById(R.id.artist);

        }
    }


    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.title.setText(music.getTitle());
        holder.album.setText(music.getAlbum());
//        holder.year.setText(music.getYear());
        holder.artist.setText(music.getArtist());

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}

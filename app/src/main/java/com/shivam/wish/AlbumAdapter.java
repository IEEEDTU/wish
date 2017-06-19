package com.shivam.wish;

/**
 * Created by Harish Gola on 07-06-2017.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {


    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView album;
        public ImageView album_art;


        public MyViewHolder(View view) {
            super(view);
            album_art = (ImageView) view.findViewById(R.id.album_image);
            album = (TextView) view.findViewById(R.id.album_name);
        }
    }


    public AlbumAdapter(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Album album = albumList.get(position);
        Bitmap bm= BitmapFactory.decodeFile(album.getAlbum_art());
        holder.album_art.setImageBitmap(bm);
        holder.album.setText(album.getAlbum());


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}


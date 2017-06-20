package com.shivam.wish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Harish Gola on 20-06-2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {
    private List<Genre> genreList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.genre_name);
        }
    }


    public GenreAdapter(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @Override
    public GenreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_list_row, parent, false);

        return new GenreAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GenreAdapter.MyViewHolder holder, int position) {

        Genre genre = genreList.get(position);
        holder.name.setText(genre.getName());

    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }
}

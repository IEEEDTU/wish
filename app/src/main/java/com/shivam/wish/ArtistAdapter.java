package com.shivam.wish;

/**
 * Created by Harish Gola on 07-06-2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViewHolder> {


    private List<Artist> artistList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView artistName;


        public MyViewHolder(View view) {
            super(view);
            artistName = (TextView) view.findViewById(R.id.artist_name);
        }
    }


    public ArtistAdapter(List<Artist> artistList) {
        this.artistList = artistList;
    }

    @Override
    public ArtistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_row, parent, false);

        return new ArtistAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtistAdapter.MyViewHolder holder, int position) {

        Artist artist = artistList.get(position);
        holder.artistName.setText(artist.getArtistName());
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }
}


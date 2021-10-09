package com.example.miskaaassignment.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miskaaassignment.R;
import com.example.miskaaassignment.model.Crew;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {

    Context context;
    List<Crew> list;

    public CrewAdapter(Context context, List<Crew> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.crewImage);
        holder.crewName.setText(list.get(position).getName());
        holder.crewAgency.setText(list.get(position).getAgency());
        holder.crewWiki.setText(list.get(position).getWikipedia());
        holder.crewStatus.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        
        ImageView crewImage;
        TextView crewName, crewAgency, crewWiki, crewStatus;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crewImage = itemView.findViewById(R.id.image_view);
            crewName = itemView.findViewById(R.id.crew_name);
            crewAgency = itemView.findViewById(R.id.crew_agency);
            crewWiki = itemView.findViewById(R.id.crew_wiki);
            crewStatus = itemView.findViewById(R.id.crew_status);
        }
    }
}

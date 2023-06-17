package com.example.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context Context;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList,Context context) {
        this.articlesArrayList = articlesArrayList;
        this.Context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
       return new NewsRVAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {
     Articles articles =articlesArrayList.get(position);
     holder.subTitleTv.setText(articles.getDescription());
     holder.titleTV.setText(articles.getTitle());
     Picasso.get().load(articles.getUrlToImage()).into(holder.newsIv);
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i= new Intent(Context,NewsDetailsActivity.class);
             i.putExtra("title",articles.getTitle());
             i.putExtra("content",articles.getContents());
             i.putExtra("desc",articles.getDescription());
             i.putExtra("image",articles.getUrlToImage());
             i.putExtra("Url",articles.getUrl());

         }
     });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV,subTitleTv;
        private ImageView newsIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTv= itemView.findViewById(R.id.idTVsubtittle);
            newsIv =itemView.findViewById(R.id.idIVnews);
        }
    }
}

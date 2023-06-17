package com.example.newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private ArrayList<CategroryRVModels>CategroryRVModels;
    private Context context;
    private CategoryClickInterface CategoryClickInterface;

    public CategoryRVAdapter(ArrayList<com.example.newsapplication.CategroryRVModels> categroryRVModels, Context context, CategoryRVAdapter.CategoryClickInterface categoryClickInterface) {
        this.CategroryRVModels = categroryRVModels;
        this.context = context;
        this.CategoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_items,parent,false);
     return new  CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
     CategroryRVModels categroryRVModels=CategroryRVModels.get(position);
     holder.categoryTV.setText(categroryRVModels.getCategory());
     Picasso.get().load(categroryRVModels.getCategoryImageUrl()).into(holder.categoryIV);
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
              CategoryClickInterface.onCategoryClick(holder.getAdapterPosition());
         }
     });

    }

    @Override
    public int getItemCount() {
        return CategroryRVModels.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int positon);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTV;
        private ImageView categoryIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV=itemView.findViewById(R.id.idTVCategory);
            categoryIV=itemView.findViewById(R.id.idIVCategorY);
        }
    }
}

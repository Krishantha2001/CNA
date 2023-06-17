package com.example.newsapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {

    //ad531bf09c4045449fec851234c35548
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles>articlesArrayList;
    private ArrayList<CategroryRVModels>categroryRVModelArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV=findViewById(R.id.idRVceylon);
        categoryRV=findViewById(R.id.idRVCategories);
        loadingPB=findViewById(R.id.idPBLoading);
        articlesArrayList =new ArrayList<>();
        categroryRVModelArrayList= new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter =new CategoryRVAdapter(categroryRVModelArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter );
        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();
    }

    private void getCategories(){
        categroryRVModelArrayList.add(new CategroryRVModels("Top","https://images.unsplash.com/photo-1518162899679-9c6f9c4b037c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTN8fEFsbHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categroryRVModelArrayList.add(new CategroryRVModels("Popular","https://images.unsplash.com/photo-1518770660439-4636190af475?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80 "));
        categroryRVModelArrayList.add(new CategroryRVModels("Science","https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c2NpZW5jZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60 "));
        categroryRVModelArrayList.add(new CategroryRVModels("Sports","https://images.unsplash.com/photo-1517649763962-0c623066013b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fHNwb3J0fGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        categroryRVModelArrayList.add(new CategroryRVModels("Genaral","https://images.unsplash.com/photo-1588681664899-f142ff2dc9b1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bmV3cyUyMGFwcHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categroryRVModelArrayList.add(new CategroryRVModels("Business","https://images.unsplash.com/photo-1664575602276-acd073f104c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fGJ1c2luZXNzfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60 "));
        categroryRVModelArrayList.add(new CategroryRVModels("Entertaiment","https://images.unsplash.com/photo-1603190287605-e6ade32fa852?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categroryRVModelArrayList.add(new CategroryRVModels("Health","https://images.unsplash.com/photo-1576091160550-2173dba999ef?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjZ8fGhlYWx0aHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoryRVAdapter.notifyDataSetChanged();
    }

private void getNews(String category){
       loadingPB.setVisibility(View.VISIBLE);
       articlesArrayList.clear();
       String categoryUrl="https://newsapi.org/v2/top-headlines?country?=sl&category="+category+"&apiKey=ad531bf09c4045449fec851234c35548";
       String url="https://newsapi.org/v2/top-headlines?country=&Domais=aljazeera.com&sortBy=publishedAt&language=en&apiKey=ad531bf09c4045449fec851234c35548";
       String BASE_URL ="https://newsapi.org/";
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofiAPI retrofiAPI=retrofit.create(RetrofiAPI.class);
    Call<NewsModel>call;
    if(category.equals("All")){
        call=retrofiAPI.getAllNews(url);

    }else{
        call=retrofiAPI.getNewsBYCategory(categoryUrl);
    }
    loadingPB.setVisibility(View.INVISIBLE);

call.enqueue(new Callback<NewsModel>() {
    @Override
    public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
     NewsModel newsModel= response.body();
     ArrayList<Articles>articles= newsModel.getArticles();
     for (int i=0; i<articles.size();i++){
         articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContents()));

     }
newsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<NewsModel> call, Throwable t) {
      Toast.makeText(MainActivity.this,"Fail to get news",Toast.LENGTH_SHORT);

    }
});

}
    @Override
    public void onCategoryClick(int positon) {
String category =categroryRVModelArrayList.get(positon).getCategory();
getNews(category);
    }
}
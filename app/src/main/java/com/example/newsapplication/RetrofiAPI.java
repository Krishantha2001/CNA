package com.example.newsapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
public interface RetrofiAPI {
@GET
Call<NewsModel> getAllNews(@Url String url);
@GET
    Call<NewsModel>getNewsBYCategory(@Url String url);


}

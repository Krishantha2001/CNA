package com.example.newsapplication;

public class Articles {
     private String title;
     private String description;
     private String urlToImage;
     private String contents;
     private String Url;
     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public String getUrlToImage() {
          return urlToImage;
     }

     public void setUrlToImage(String urlToImage) {
          this.urlToImage = urlToImage;
     }

     public String getContents() {
          return contents;
     }

     public void setContents(String contents) {
          this.contents = contents;
     }

     public String getUrl() {
          return Url;
     }

     public void setUrl(String url) {
          Url = url;
     }

     public Articles(String title, String description, String urlToImage, String contents,String Url) {
          this.title = title;
          this.description = description;
          this.urlToImage = urlToImage;
          this.Url =Url;
          this.contents = contents;
     }
}

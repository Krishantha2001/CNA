package com.example.newsapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
     String title,desc,content,imageURL,Url;
     private TextView titleTv,subDescTv,contentTV;
     private ImageView newsIv;
     private Button readNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        title =getIntent().getStringExtra("title");
        desc =getIntent().getStringExtra("desc");
        content=getIntent().getStringExtra("content");
        imageURL=getIntent().getStringExtra("image");
        Url=getIntent().getStringExtra("Url");
        titleTv=findViewById(R.id.idTVTitle);
        subDescTv=findViewById(R.id.idTVSubDesc);
        contentTV=findViewById(R.id.idTVContents);
        newsIv=findViewById(R.id.idIVnews);
        readNewsBtn=findViewById(R.id.idBtnReadNews);
        titleTv.setText(title);
        subDescTv.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageURL).into(newsIv);
        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                startActivity(i);
            }
        });

    }
}
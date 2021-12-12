package com.course.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// 제품에 대한 details

public class Details extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        button = (Button)findViewById(R.id.button4);
        getIncomingIntent();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("url")));
                startActivity(intent);
            }
        });
    }

    private void getIncomingIntent(){
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String url = getIntent().getStringExtra("url");
        String details = getIntent().getStringExtra("details");

        byte[] arr = getIntent().getByteArrayExtra("image1");
        Bitmap image1 = BitmapFactory.decodeByteArray(arr, 0, arr.length);

        byte[] arr2 = getIntent().getByteArrayExtra("image2");
        Bitmap image2 = BitmapFactory.decodeByteArray(arr2, 0, arr2.length);

        setData(name, price, url, details, image1, image2);
    }

    private void setData(String name, String price, String url, String details, Bitmap img1, Bitmap img2){

        TextView pro_name = findViewById(R.id.textView);
        TextView pro_price = findViewById(R.id.textView2);
        TextView pro_url = findViewById(R.id.textView3);
        TextView pro_details = findViewById(R.id.textView8);

        ImageView pro_img1 = findViewById(R.id.imageView2);
        ImageView pro_img2 = findViewById(R.id.imageView3);

        pro_name.setText(name);
        pro_price.setText(price);
        pro_url.setText(url);
        pro_details.setText(details);
        pro_img1.setImageBitmap(img1);
        pro_img2.setImageBitmap(img2);
    }
}
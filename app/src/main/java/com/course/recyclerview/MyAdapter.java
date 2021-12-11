package com.course.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/*
MyAdapter 클래스에 꼭 들어가야할 것
1. onCreateViewHolder
2. onBindViewHolder
3. getItemCount
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView myPicture;    //제품이미지
        TextView myText;          //제품명

        MyViewHolder(View view) {
            super(view);
            myPicture = view.findViewById(R.id.imageView);
            myText = view.findViewById(R.id.textView);
        }
    }

    private ArrayList<Product> ProductList;

    MyAdapter(ArrayList<Product> schools){
        this.ProductList=schools;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position){
        MyViewHolder myViewHolder = holder;
        myViewHolder.myPicture.setImageResource(ProductList.get(position).getImageID1());
        myViewHolder.myText.setText(ProductList.get(position).getSchoolName());

        myViewHolder.myPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Details.class);

                intent.putExtra("name", ProductList.get(position).getSchoolName());
                intent.putExtra("price", ProductList.get(position).getImagePrice());
                intent.putExtra("url", ProductList.get(position).getURL());
                intent.putExtra("details", ProductList.get(position).getDetails());

                //이미지 받아오기
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Bitmap sendBitmap = BitmapFactory.decodeResource(context.getResources(), ProductList.get(position).getImageID1());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG,10, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image1",byteArray);

                Bitmap sendBitmap2 = BitmapFactory.decodeResource(context.getResources(), ProductList.get(position).getImageID2());
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                sendBitmap2.compress(Bitmap.CompressFormat.JPEG,10, stream2);
                byte[] byteArray2 = stream2.toByteArray();
                intent.putExtra("image2", byteArray2);

                context.startActivity(intent);
            }
        });

        myViewHolder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Details.class);

                intent.putExtra("name", ProductList.get(position).getSchoolName());
                intent.putExtra("price", ProductList.get(position).getImagePrice());
                intent.putExtra("url", ProductList.get(position).getURL());
                intent.putExtra("details", ProductList.get(position).getDetails());

                //이미지 받아오기
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Bitmap sendBitmap = BitmapFactory.decodeResource(context.getResources(), ProductList.get(position).getImageID1());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG,10, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image1",byteArray);

                Bitmap sendBitmap2 = BitmapFactory.decodeResource(context.getResources(), ProductList.get(position).getImageID2());
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                sendBitmap2.compress(Bitmap.CompressFormat.JPEG,10, stream2);
                byte[] byteArray2 = stream2.toByteArray();
                intent.putExtra("image2", byteArray2);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return ProductList.size();
    }
}

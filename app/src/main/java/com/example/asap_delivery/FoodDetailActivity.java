package com.example.asap_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class FoodDetailActivity extends AppCompatActivity {
    TextView mTitleTv, mDetailTv;
    ImageView mImageIv;
    Bitmap bitmap;
    Button mAddOrderBtn;
    public static List<FoodItems> order = FoodMenuActivity.order;
    public static Map<String, FoodItems> foodList = FoodMenuActivity.foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //initialize views
        mTitleTv = findViewById(R.id.titleTv);
        mDetailTv = findViewById(R.id.descriptionTv);
        mImageIv = findViewById(R.id.imageView);
        mAddOrderBtn = findViewById(R.id.addOrderBtn);

        FoodItems food = foodList.get(getIntent().getStringExtra("title"));

        //get data from intent
        String image = food.getImage();
        String title = food.getTitle();
        String desc = food.getDescription();
        String price = food.getPrice();

        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
        Picasso.get().load(image).into(mImageIv);

        //get image from imageview as bitmap
        bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();

        mAddOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder();
            }
        });
    }

    private void addToOrder(){
        order.add(foodList.get(mTitleTv));
    }
}

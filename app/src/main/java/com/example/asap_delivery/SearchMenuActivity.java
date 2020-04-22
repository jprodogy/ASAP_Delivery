package com.example.asap_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchMenuActivity extends AppCompatActivity {
    private static final String TAG = "SearchMenuActivity";
    private ListView listView;
    private Map<String, FoodItems> foodList = FoodMenuActivity.foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);

        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        listView = (ListView) findViewById(R.id.food_list);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        String searchWord = getIntent().getStringExtra("Keyword");
        List<String> foundFood = new ArrayList();
        for (FoodItems food: foodList.values()){
            if (food.keywords.contains(searchWord.toLowerCase())){
                foundFood.add(food.title);
            }
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, foundFood) {};
        listView.setAdapter(adapter);
    }

}

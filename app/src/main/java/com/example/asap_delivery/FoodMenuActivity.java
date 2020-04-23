package com.example.asap_delivery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asap_delivery.extra.PostDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FoodMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "FoodMenuActivity";
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    private SearchView searchBar;
    private DatabaseReference mRef;
    private DatabaseReference mRef2;
    private FirebaseDatabase mFirebaseDatabase;
    public static List<FoodItems> order;
    public static Map<String, FoodItems> foodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        foodList = new HashMap<>();
        order = new ArrayList<>();

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRef = mFirebaseDatabase.getReference("Data");
        mRef2 = mFirebaseDatabase.getReference("Data2");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FoodItems food = snapshot.getValue(FoodItems.class);
                    foodList.put(food.title, food);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        searchBar = findViewById(R.id.search_bar);

        recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView2 = (RecyclerView)findViewById(R.id.imagegallery2);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);


        searchBar.setOnQueryTextListener(this);

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        Intent myIntent = new Intent(this, SearchMenuActivity.class);
        myIntent.putExtra("Keyword", s);
        startActivity(myIntent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<FoodItems, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<FoodItems, ViewHolder>(
                        FoodItems.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, FoodItems food, int position) {
                        viewHolder.setDetails(getApplicationContext(), food.getTitle(), food.getDescription(), food.getImage());
                        Log.d(TAG, food.getTitle());
                    }



                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                                ImageView mImageView = view.findViewById(R.id.rImageView);
                                //get data from views
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), FoodDetailActivity.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                Log.d(TAG, String.valueOf(bytes.length));
                               // intent.putExtra("image", bytes); //put bitmap image as array of bytes
                                intent.putExtra("title", mTitle); // put title
                               // intent.putExtra("description", mDesc); //put description
                                startActivity(intent); //start activity


                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }

                };


        //set adapter to recyclerview
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }
}
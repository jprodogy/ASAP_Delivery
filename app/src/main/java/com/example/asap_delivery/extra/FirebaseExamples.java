package com.example.asap_delivery.extra;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asap_delivery.FirebaseImageAdapter;
import com.example.asap_delivery.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirebaseExamples extends AppCompatActivity {
    private static final String TAG = "FirebaseExamples";

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageRef;
    private StorageReference pathReference;
    private StorageReference gsReference;
    private StorageReference httpsReference;
    ImageView imageView;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        try {
            File localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView = (RecyclerView)findViewById(R.id.imagegallery);

        mFirebaseStorage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        mStorageRef = mFirebaseStorage.getReference();
        // Create a reference with an initial file path and name
        pathReference = mStorageRef.child("Food");
        // Create a reference to a file from a Google Cloud Storage URI
        gsReference = mFirebaseStorage.getReferenceFromUrl("gs://asapdelivery-8fd35.appspot.com/Food/Tenders&Fries.jpg");
        // Create a reference from an HTTPS URL
        // Note that in the URL, characters are URL escaped!
        httpsReference = mFirebaseStorage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/asapdelivery-8fd35.appspot.com/o/Food%2FTenders%26Fries.jpg?alt=media&token=4c80adfa-c097-4faf-b3f9-656ec29bcc3e");

        try {
            Download();
            //DownloadAll();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "You have failed this city ");
        }
    }

    public void Download() throws IOException {
        File localFile = File.createTempFile("Food", "png");
        mStorageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                        Log.d(TAG, "onSuccess: ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    //Download in memory
    public void Download2(){
        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Log.d(TAG, "onSuccess: ");
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ImageView image = (ImageView) findViewById(R.id.rImageView);

                image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(), image.getHeight(), false));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    //Download images using Firbase UI
    public void Download3(){
        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        // ImageView in your Activity
        imageView = findViewById(R.id.rImageView);

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        Glide.with(this /* context */)
                .load(storageReference)
                .into(imageView);


    }

    //Download Data via URL
    public void Download4(){

        mStorageRef.child("users/me/profile.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                Log.d(TAG, "onSuccess: ");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    //Download files Locally
    public void Download5() throws IOException {
        StorageReference islandRef = mStorageRef.child("images/island.jpg");

        File localFile = File.createTempFile("images", "jpg");

        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {


                // Local temp file has been created
                Log.d(TAG, "onSuccess: ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void DownloadAll(){
        final List<StorageReference> storageList = new ArrayList<>();
        StorageReference listRef = mFirebaseStorage.getReference().child("Food");
        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            //Log.d(TAG, prefix.getName());
                        }

                        for (StorageReference item : listResult.getItems()) {
                            storageList.add(item);

                        }


                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
                    }
                });

        /*
        FirebaseImageAdapter firebaseImageAdapter = new FirebaseImageAdapter(getApplicationContext(), storageList);
        Log.d(TAG, String.valueOf(firebaseImageAdapter.getItemCount()));
        recyclerView.setAdapter(firebaseImageAdapter);
*/
    }
}

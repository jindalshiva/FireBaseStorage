package com.example.nandkishorjindal.firebasestorage;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonGallery, buttonCamera,buttonUpdate;
    private ImageView imageView;
    private Uri file;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = (Button) findViewById(R.id.button_photo);
        buttonGallery = (Button) findViewById(R.id.button_Gallery);
        imageView = (ImageView) findViewById(R.id.imageview);
        buttonUpdate = (Button) findViewById(R.id.button_update);

        mStorage = FirebaseStorage.getInstance().getReference();

        buttonGallery.setOnClickListener(this);
        buttonCamera.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == buttonCamera){
            //choose camera
            takePhoto();


        }
        else if(v == buttonGallery)
        {
            //choose gallery
            showFileChooser();

        }
        else if(v== buttonUpdate){
            //upload file
            uploadFile();
        }
    }

    private void uploadFile() {
    }

    private void showFileChooser() {
    }

    private void takePhoto() {
    }

}


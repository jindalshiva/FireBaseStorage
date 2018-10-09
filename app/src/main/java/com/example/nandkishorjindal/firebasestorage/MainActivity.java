package com.example.nandkishorjindal.firebasestorage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                buttonCamera.setEnabled(true);
            }
        }
    }

    private void uploadFile() {

    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/").putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());

        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent,100);
    }
    private static File getOutputMediaFile () {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }


}


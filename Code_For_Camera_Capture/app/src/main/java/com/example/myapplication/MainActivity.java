package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    //variable
  Button btOpen;
  Button btSave;
  ImageView imageView;
  OutputStream outputStream;
  @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign variable
        imageView= findViewById(R.id.imageView);
        btOpen=findViewById(R.id.btnTakePio);
        btSave=findViewById(R.id.bt_save);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable=(BitmapDrawable)imageView.getDrawable();
                Bitmap bitmap=drawable.getBitmap();
                File filepath= Environment.getExternalStorageDirectory();
                File dir=new File(filepath.getAbsolutePath()+"/Demo/");
                dir.mkdir();
                File file= new File(dir,System.currentTimeMillis()+"/.jpg/");
                try{
                    outputStream=new FileOutputStream(file);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                Toast.makeText(getApplicationContext(),"Image Save To Internal !!!"
                ,Toast.LENGTH_SHORT).show();
                try{
                    outputStream.flush();
                }catch(IOException e){
                    e.printStackTrace();
                }
                try{
                    outputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

         //request for Camera permission
        if(ContextCompat.checkSelfPermission( MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this , new String []{Manifest.permission.CAMERA}, 100);
        }
        btOpen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //open camera
                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100); }});
    }
        @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 100) {
                //  Bundle extras = data.getExtras();
                //get caputer image
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                // set caputer image in imageview
                imageView.setImageBitmap(imageBitmap);
            }
        }

   // public void savePicture(View view) {
   // }
}



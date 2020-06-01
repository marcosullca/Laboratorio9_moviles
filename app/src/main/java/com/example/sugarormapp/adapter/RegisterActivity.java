package com.example.sugarormapp.adapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sugarormapp.MainActivity;
import com.example.sugarormapp.R;
import com.example.sugarormapp.UserRepository;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class RegisterActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CAMERA =0012 ;
    public static final int REQUEST_CODE_GALLERY =0013 ;
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private ImageView btn_camera_java;
    private ImageView photo_camera_java;
    private String [] items = {"Camera","Gallery"};
    private String path_camera_java;
    private String path_gallery_java;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = (EditText)findViewById(R.id.fullname_input);
        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        btn_camera_java=findViewById(R.id.btn_camera);
        photo_camera_java=findViewById(R.id.res_camera);

        btn_camera_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goImagen();
            }
        });



    }
    private void goImagen(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elija una opcion");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("camara")){
                    EasyImage.openCamera(RegisterActivity.this,REQUEST_CODE_CAMERA);
                }else if(items[i].equals("galleria")){
                    EasyImage.openGallery(RegisterActivity.this,REQUEST_CODE_GALLERY);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type){
                    case REQUEST_CODE_CAMERA:
                        Glide.with(RegisterActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(photo_camera_java);
                        path_camera_java= imageFile.getAbsolutePath();
                        break;
                    case REQUEST_CODE_GALLERY:
                        Glide.with(RegisterActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(photo_camera_java);
                        path_gallery_java= imageFile.getAbsolutePath();
                        break;
                }
            }
        });
    }


    public void callRegister(View view){
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(fullname.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(path_camera_java ==null){
            UserRepository.create(fullname, email, password,path_gallery_java);
            finish();
        }else{
            UserRepository.create(fullname, email, password,path_camera_java);
            finish();
        }


    }

}

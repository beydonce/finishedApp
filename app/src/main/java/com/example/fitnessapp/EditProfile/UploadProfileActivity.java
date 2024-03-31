package com.example.fitnessapp.EditProfile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fitnessapp.MainActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.Users.User;
import com.example.fitnessapp.Users.UserOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadProfileActivity extends AppCompatActivity {
    EditText uploadName;
    ImageView uploadImage;
    Button saveButton;
    private Uri uri;
    private Bitmap bitmapImage;
    UserOpenHelper dbHelper;
    long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile);
        uploadImage = findViewById(R.id.uploadImage);
        uploadName = findViewById(R.id.uploadName);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new UserOpenHelper(this);
        userId = getIntent().getLongExtra("userId", -1);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            assert data != null;
                            uri = data.getData();
                            try {
                                bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            } catch (IOException e) {
                                Toast.makeText(UploadProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            uploadImage.setImageBitmap(bitmapImage);
                        } else {
                            Toast.makeText(UploadProfileActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    activityResultLauncher.launch(intent);
                } catch (Exception e){
                    Toast.makeText(UploadProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeImage();
            }
        });
    }
    private void storeImage(){
        if (!uploadName.getText().toString().isEmpty()
                && uploadImage.getDrawable() != null && bitmapImage != null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] blob = stream.toByteArray();
            bitmapImage.recycle();

            dbHelper.open();
            User user = dbHelper.getById(userId);
            user.setName(uploadName.getText().toString());
            user.setImage(blob);

            dbHelper.updateByRow(user);
            dbHelper.close();
            Intent intent = new Intent(UploadProfileActivity.this, MainActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Fields are mandatory", Toast.LENGTH_SHORT).show();
        }
    }
}
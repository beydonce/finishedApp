package com.example.fitnessapp.Users;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fitnessapp.databinding.ActivitySignupBinding;
public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    UserOpenHelper databaseHelper;
    IsCheck isCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new UserOpenHelper(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();
                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        databaseHelper.open();
                        Boolean checkUserEmail = IsCheck.checkEmail(email);
                        Boolean doesUserExist = databaseHelper.getByEmail(email);
                        databaseHelper.close();
                        if(checkUserEmail == false && doesUserExist == false){
                            databaseHelper.open();
                            User insert = databaseHelper.createUser(new User(email, null, password, null));
                            databaseHelper.close();
                            if(insert != null){
                                Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
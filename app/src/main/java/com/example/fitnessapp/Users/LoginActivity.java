package com.example.fitnessapp.Users;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.MainActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.ActivityLoginBinding;
//import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    UserOpenHelper databaseHelper;

    TextView forgotPassword;
//    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgotPassword = findViewById(R.id.forgotpass);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new UserOpenHelper(this);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if(email.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    databaseHelper.open();
                    long id = databaseHelper.getIdByEmailPassword(email, password);
                    databaseHelper.close();
                    if(id > -1){
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userId", id);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

//        binding.forgotpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
//                startActivity(intent);
//            }
//        });

//        forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot,null);
//                EditText emailBox = dialogView.findViewById(R.id.email_box);
//
//
//                builder.setView(dialogView);
//                AlertDialog dialog = builder.create();
//
//                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String userEmail = emailBox.getText().toString();
//                        if(TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
//                            Toast.makeText(LoginActivity.this,"Enter your registered email id",Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()){
//                                    Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
//                                    dialog.dismiss();
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                });
//
//                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                if (dialog.getWindow() != null){
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                }
//                dialog.show();
//
//            }
//        });
    }
}
package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    EditText pincodeid, cnpin;
    Button btnsign;
    TextView clck;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide Title bar
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        pincodeid = findViewById(R.id.editText);
        cnpin = findViewById(R.id.editText2);
        btnsign = findViewById(R.id.btnSignUp);
        clck = findViewById(R.id.textView4);

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = pincodeid.getText().toString();
                String pwd = cnpin.getText().toString();
                if(email.isEmpty()){
                    pincodeid.setError("Please enter email id");
                    pincodeid.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    cnpin.setError("Please enter your password");
                    cnpin.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        clck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

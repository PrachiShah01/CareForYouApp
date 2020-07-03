package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        email = (EditText)findViewById(R.id.emailLogin);
        password  = (EditText)findViewById(R.id.passwordLogin);
        login = (Button)findViewById(R.id.btnLogin);
        signup = (Button)findViewById(R.id.signbtn);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        login.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                validate(email.getText().toString(),password.getText().toString());
            }
        });

        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,PasswordActivity.class));
            }
        });

    }
    private void validate(String useremail, String userpassword){


        if(useremail.equals("")){
            Toast.makeText(loginActivity.this,"Enter the Email Id.",Toast.LENGTH_SHORT).show();
        }else if(userpassword.equals("")){
            Toast.makeText(loginActivity.this,"Enter the Password.",Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setMessage("Wait for Login...");
            progressDialog.show();
            clickLogin(useremail,userpassword);
        }
    }
    public void clickLogin(String user_name,String password){

        firebaseAuth.signInWithEmailAndPassword(user_name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
//                    Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(loginActivity.this,"Login Failed...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(loginActivity.this,HomeActivity.class));
        }else{
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}

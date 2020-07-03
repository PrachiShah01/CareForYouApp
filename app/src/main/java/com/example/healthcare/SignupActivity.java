package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.View.Z;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, password, Age;
    private TextView login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    String useremail,username,userAge,userpassword;
    // add this variable to hold gender value.
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        clickSignup();

        ImageView userProfilepic = (ImageView) findViewById(R.id.imageSignup);
        android.widget.RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton genderBtn = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        gender = genderBtn.getText().toString();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioMale:
                        gender = "Male";
                        break;
                    case R.id.radioFemale:
                        gender = "Female";
                        break;
                    case R.id.radioOther:
                        gender = "Female";
                        break;
                }
            }
        });

        //For Already Signedin And go to Login Activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, loginActivity.class));
            }
        });
    }
    public void clickSignup() {
        name = (EditText)findViewById(R.id.Name);
        email = (EditText)findViewById(R.id.Mail1);
        password = (EditText)findViewById(R.id.Password1);
        Button signup = (Button) findViewById(R.id.Signupbtn);
        login = (TextView)findViewById(R.id.tvLogin);
        Age = (EditText)findViewById(R.id.tvAge);

        //On Signup button click
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validate()){
                    //Upload data to the DATABASE.
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendEmailVerification();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean validate(){

        Boolean result = false;
        username = name.getText().toString();
        useremail = email.getText().toString();
        userpassword = password.getText().toString();
        userAge = Age.getText().toString();

        if(username.equals("")){
            Toast.makeText(SignupActivity.this,"Please enter your name",Toast.LENGTH_SHORT).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
            email.setError("Please Enter Valid Email ID");
        }else if(userpassword.length()<6){
            password.setError("Please Enter password minimum in 6 character");
        }else if(!userAge.matches("[0-9]{2}")){
            Age.setError("Please Enter your Age");
        }
        else{
            progressDialog.setMessage("Wait for SignUp...");
            progressDialog.show();
            result = true;
            // Toast.makeText(this,"Signup Successful...",Toast.LENGTH_SHORT).show();
            /* Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();*/
        }
        return result;
    }

    private void sendEmailVerification(){
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        firebaseAuth.signOut();
                        Toast.makeText(SignupActivity.this, "Successfully Signup, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this,loginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(SignupActivity.this, "Verification mail has not been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //to store data into database
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(useremail,username,userAge,gender);
        myref.setValue(userProfile);
    }
}

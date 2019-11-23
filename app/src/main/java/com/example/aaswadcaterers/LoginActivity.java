package com.example.aaswadcaterers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    Button userlogin;
    EditText username,Password;
    FirebaseAuth mAuth;
    ProgressDialog prg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        userlogin=findViewById(R.id.btnlogin);

        username=findViewById(R.id.edemail);
        Password=findViewById(R.id.edpassword);
        prg= new ProgressDialog(this);



        mAuth=FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            Toast.makeText(LoginActivity.this,"SignIn Successful", Toast.LENGTH_LONG).show();
        }


//
    }

    public void userLogin() {
//                userlogin.setOnClickListener((View.OnClickListener) this);

//            When user will login...
        String email=username.getText().toString().trim();
        String password=Password.getText().toString().trim();


        prg.setMessage("Logging In");
        prg.show();


        if (mAuth.getCurrentUser()!=null){
            prg.dismiss();
            FirebaseUser current=mAuth.getCurrentUser();
            String Current = current.getEmail();
            Toast.makeText(this,"Already Logged in"+Current,Toast.LENGTH_LONG).show();
            return;
        }

        else
        if (mAuth.getCurrentUser()==null){

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            prg.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                String User=user.getEmail();

                                Toast.makeText(getApplicationContext(), "Successfully Signed in As "+User,Toast.LENGTH_LONG+20).show();

                            } else {
                                // If sign in fails, display a message to the user...
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_LONG).show();

                            }

                            // ...
                        }
                    });

        }
    }

    public void onClick(View v){
        if (v==userlogin){
            userLogin();
        }
    }

    public void signup(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }

    public void userLogin(View view) {
        userLogin();
    }
}


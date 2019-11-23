package com.example.aaswadcaterers;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    FirebaseDatabase fd;
    DatabaseReference db,dbloc;
    EditText name,Email,Password,contact,addrs,edsrn;
    RadioButton teacher,student;
    Button signup,cancel;
    ProgressDialog prgr;
    RadioGroup idnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        db=FirebaseDatabase.getInstance().getReference("college");

        name=findViewById(R.id.edName);
        Email=findViewById(R.id.edEmail);
        Password=findViewById(R.id.edPassword);
        contact=findViewById(R.id.edMobile);
        addrs=findViewById(R.id.edAddrs);
        signup=findViewById(R.id.btnSignup);
        prgr=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        idnumber = findViewById(R.id.radio);
        edsrn=findViewById(R.id.edsrn);


        String Name,email,password,Addrs,Contact,isteache;


        Name=name.getText().toString().trim();
        email=Email.getText().toString().trim();
        password=Password.getText().toString().trim();
        Contact= String.valueOf(contact);
        Addrs=addrs.getText().toString();


    }

    public void signUp(View view) {

        registerUser();
    }




    private void registerUser() {

        final String Name,email,password,Addrs,Contact,srn;
        final Boolean teach;

        Name=name.getText().toString().trim();
        email=Email.getText().toString().trim();
        password=Password.getText().toString().trim();
        Contact= contact.getText().toString();
        Addrs=addrs.getText().toString();
        srn=edsrn.getText().toString().trim();
//        teach=teacher.get

        int selectedId = idnumber.getCheckedRadioButtonId();
//        String typeuser = idnumber
//        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        final RadioButton radioButton = (RadioButton) findViewById(selectedId);

        String isTeacher=radioButton.getText().toString();









        String man;
        Toast.makeText(this,"You are a  = "+isTeacher,Toast.LENGTH_LONG).show();
        // find the radiobutton by returned id
//        RadioButton radioButton = (RadioButton) findViewById(selectedId);
//
//        final String isteacher = radioButton.getText().toString();



        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Contact)) {
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Addrs)) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(srn)) {
            Toast.makeText(this, "Enter SRN", Toast.LENGTH_SHORT).show();
            return;
        }

        prgr.setMessage("Signing Up Please Wait");
        prgr.show();



        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prgr.dismiss();
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String User = user.getEmail();
                            Toast.makeText(SignupActivity.this, "Account creation Successful \n" + User, Toast.LENGTH_LONG).show();
                            saveuserdetails();
                        } else {
                            Exception exc = task.getException();

                            Toast.makeText(SignupActivity.this, "error "+exc, Toast.LENGTH_LONG).show();
                        }
                    }

                    private void saveuserdetails() {

                        String isteach=radioButton.getText().toString();
                        FirebaseUser user = mAuth.getCurrentUser();
                        String emailid = user.getEmail();
                        String userid = user.getUid();
                        String srn=edsrn.getText().toString().trim();
                        saveuser Saveuser=new saveuser(Name,Contact,Addrs,isteach,userid,emailid,srn);

//                        shopdetails Saveinfo=new shopdetails(timingfro,timingt,sname, shoptype, sphon, semail,  saddrs, latng, sdelivery,registerdate,sowner);

                        FirebaseAuth fauth=FirebaseAuth.getInstance();
                        fauth.getCurrentUser();
//                        FirebaseUser user=fauth.getCurrentUser();
                        if (user!=null)
                        {
                            Toast.makeText(getApplicationContext(),"Ohhh yeahh",Toast.LENGTH_SHORT).show();
                        }

                        db = FirebaseDatabase.getInstance().getReference();
                        db.child(user.getUid()).setValue(Saveuser);


//                        saveuser Saveuser=new saveuser(Name,Contact,Addrs,Shopis);

//                        if (db.child(User).setValue(Saveuser).isSuccessful()){
//
//                            Toast.makeText(getApplicationContext(),"it should save user bu t lets see",Toast.LENGTH_LONG).show();
//                        } else Toast.makeText(getApplicationContext(),"those details csant",Toast.LENGTH_LONG).show();
//
//                        db.push().setValue(currentuser);
//                        dbloc=FirebaseDatabase.getInstance().getReference(currentuser);

//                  TODO
//                       dbloc.push().child("longitude").setValue(lng);
//                        dbloc.push().child("lattitude").setValue(lat);
//                        dobloc.push().child("uid").setvalue(currentuser);


//                        Toast.makeText(getApplicationContext(),"User Registered successfully",Toast.LENGTH_LONG).show();



                    }
                });
    }

    @Override
    public void onClick(View view) {
    }

    public void Cancelto(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }



}


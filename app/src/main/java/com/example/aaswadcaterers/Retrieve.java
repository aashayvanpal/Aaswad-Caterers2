package com.example.aaswadcaterers;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Retrieve extends AppCompatActivity {
    ListView listView;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        user=new User();
        mAuth=FirebaseAuth.getInstance();

        listView=findViewById(R.id.ListView);

        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();
        adapter =new ArrayAdapter<String>(getApplicationContext(),R.layout.user_info,R.id.userInfo, list);

        ref=FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    user=ds.getValue(User.class);
                    String ram= (String)user.getName().toString();
                    list.add(user.getName().toString());


                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}


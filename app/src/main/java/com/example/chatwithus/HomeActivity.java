package com.example.chatwithus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatwithus.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    //CircleImageView circleImageView;
   // TextView name;
    TextView textView;
    ImageView ing;
    FirebaseUser firebaseUser;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView=findViewById(R.id.tcs);
        ing=findViewById(R.id.ing);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");



       // circleImageView=findViewById(R.id.civ);
      //name=findViewById(R.id.name);
       // firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
       // reference= FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid());
       // reference.addValueEventListener(new ValueEventListener() {
           /* @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
               //  name.setText(user.getName());
                if(user.getImageurl().equals("default"))
                {
                   // circleImageView.setImageResource(R.mipmap.ic_launcher);
                }
                else
                {
                    //Glide.with(HomeActivity.this).load(user.getImageurl()).into(circleImageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;

            }
        });*/
           textView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent(HomeActivity.this,ComActivity.class);
                   startActivity(intent);
               }
           });
        ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,NavActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,StartActivity.class));
                finish();
                return true;
        }
        return false;
    }





    }



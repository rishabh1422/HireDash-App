package com.example.chatwithus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText name,company,marks10,marks12,UG,email,pass;
    Button register;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        name=findViewById(R.id.name);
        company=findViewById(R.id.company);
        marks10=findViewById(R.id.marks10);
        marks12=findViewById(R.id.marks12);
        UG=findViewById(R.id.UG);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        register=findViewById(R.id.register);

auth= FirebaseAuth.getInstance();
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String nam = name.getText().toString();
        String com = company.getText().toString();
        String m10 = marks10.getText().toString();
        String m12 = marks12.getText().toString();
        String ug= UG.getText().toString();
        String em=email.getText().toString();
        String pas=pass.getText().toString();
        if(TextUtils.isEmpty(nam)||TextUtils.isEmpty(com)||TextUtils.isEmpty(m10)||TextUtils.isEmpty(m12)||TextUtils.isEmpty(ug)||TextUtils.isEmpty(em)||TextUtils.isEmpty(pas))
        {
            Toast.makeText(RegisterActivity.this, "All Filling Are Required", Toast.LENGTH_SHORT).show();
        }
        else if(pas.length()<6)
        {
            Toast.makeText(RegisterActivity.this, "Password Must be 6 Characters", Toast.LENGTH_SHORT).show();
        }
        else
        {
            register(nam,com,m10,m12,ug,em,pas);
        }
    }
});

    }
    private void register(final String name, final String company , final String marks10, final String marks12, final String UG, String email, String pass)
    {
       auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful())
               {
                   FirebaseUser firebaseUser=auth.getCurrentUser();
                   String userid=firebaseUser.getUid();
                   reference= FirebaseDatabase.getInstance().getReference("Users ").child(userid);
                   HashMap<String ,String> hashMap= new HashMap<>();
                   hashMap.put("id",userid);
                   hashMap.put("Student Name",name);
                   hashMap.put("Company Name",company);
                   hashMap.put("10th Marks",marks10);
                   hashMap.put("12th Marks ",marks12);
                   hashMap.put("UG Marks",UG);
                   hashMap.put("ImageUrl","default");
                   reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                                   Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                           }
                           else
                           {
                               Toast.makeText(RegisterActivity.this, "You can not Register both email or Password", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

               }

           }
       });

    }


}

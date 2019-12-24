package com.example.chatwithus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ComActivity extends AppCompatActivity {
    String [] tcs={"Eligibility Criteria : 60 % with No Standing Aries ","Company Name : Tata Consultancy Services","Stipend  : 21000 for 6 Month","Then Post Conformation : CTC 3.8 LPA","Job Location : Pune/Bangalore/Mumbai","Skills Required : C++/java/ Mobile Development"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        listView=findViewById(R.id.lv);
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(this,R.layout.mylist,tcs);
        listView.setAdapter(arrayAdapter);
    }
}

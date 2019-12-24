package com.example.chatwithus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoActivity extends AppCompatActivity {
    String [] info={"Eligibility Criteria : 60 % with No Standing Aries ","Company Name : Infosys","Stipend  : 21000 for 6 Month","Then Post Conformation : CTC 3.8 LPA","Job Location : Noida/Bangalore/Pune","Skills Required : Full Stack/C++/java/ Mobile Development"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        /*listView=findViewById(R.id.lv);
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(this,R.layout.mylist,info);
        listView.setAdapter(arrayAdapter);*/
    }
}

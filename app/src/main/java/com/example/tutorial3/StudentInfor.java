package com.example.tutorial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StudentInfor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_infor);

        TextView name = (TextView) findViewById(R.id.name);
        TextView program = (TextView) findViewById(R.id.program);
        TextView scholarship = (TextView) findViewById(R.id.scholarship);

        Intent intent = getIntent();
        if (intent.getExtras() != null){
            name.setText((String) intent.getExtras().get("name"));
            program.setText((String)intent.getExtras().get("program"));
            scholarship.setText((String) Boolean.toString((Boolean)intent.getExtras().get("scholarship")));
        }
    }
}
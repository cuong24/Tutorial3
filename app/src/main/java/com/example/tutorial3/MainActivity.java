package com.example.tutorial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.nameInput);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.programRadioGroup);
                RadioButton program = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                CheckBox scholarship = (CheckBox) findViewById(R.id.scholarship);
                CheckBox specialNeeds = (CheckBox) findViewById(R.id.specialNeeds);
                Intent intent = new Intent(MainActivity.this, StudentInfor.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("program", program.getText().toString());
                intent.putExtra("scholarship", scholarship.isChecked());
                intent.putExtra("specialNeeds", specialNeeds.isChecked());

                startActivityForResult(intent, 1);
            }
        });
    }
}
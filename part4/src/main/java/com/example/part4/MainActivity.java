package com.example.part4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.list);

//        Use object as an example

//        UserAccount tom = new UserAccount("Tom","admin");
//        UserAccount jerry = new UserAccount("Jerry","user");
//        UserAccount donald = new UserAccount("Donald","guest", false);
//
//        UserAccount[] users = new UserAccount[]{tom,jerry, donald};
//
//
//        // android.R.layout.simple_list_item_1 is a constant predefined layout of Android.
//        // used to create a ListView with simple ListItem (Only one TextView).
//
//        ArrayAdapter<UserAccount> arrayAdapter
//                = new ArrayAdapter<UserAccount>(this, android.R.layout.simple_list_item_1 , users);
//
//        listView.setAdapter(arrayAdapter);

        String[] values = new String[] {
                "Android List View",
                "Adapter View",
                "haha",
                "hah1",
                "hah2",
                "haha3",
                "haha4",
                "haha5",
                "haha6",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha7",
                "haha8"
        };

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = listView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this,value, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
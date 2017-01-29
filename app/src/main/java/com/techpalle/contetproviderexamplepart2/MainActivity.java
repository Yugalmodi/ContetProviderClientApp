package com.techpalle.contetproviderexamplepart2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextSubject;
    Button buttonInsert, buttonRead;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = (EditText) findViewById(R.id.name);
        editTextSubject = (EditText) findViewById(R.id.sub);
        buttonInsert = (Button) findViewById(R.id.button_insert);
        buttonRead = (Button) findViewById(R.id.button_read);
        textView = (TextView) findViewById(R.id.text_view);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver contentResolver = getContentResolver();
                String name = editTextName.getText().toString();
                String subject = editTextSubject.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put(UriProvider.NAME, name);
                contentValues.put(UriProvider.SUB, subject);
                contentResolver.insert(UriProvider.STUDENT_URI, contentValues);
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver contentResolver = getContentResolver();
                Cursor c = contentResolver.query(UriProvider.STUDENT_URI, null, null, null, null);
                StringBuilder builder = new StringBuilder();
                if(c!=null){
                    while (c.moveToNext()){
                        int no = c.getInt(0);
                        String name = c.getString(1);
                        String sub = c.getString(2);
                        builder.append("No: "+no+" Name : "+name+" Subject: "+sub+"\n\n");
                    }
                    textView.setText("Table Values:\n"+builder.toString());
                }
            }
        });
    }
}

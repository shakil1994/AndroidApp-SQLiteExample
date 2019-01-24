package com.example.shakil.sqlitedemoproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakil.sqlitedemoproject.Database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtAge, edtGender;
    private Button btnAddData;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);

        btnAddData = findViewById(R.id.btnAddData);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String age = edtAge.getText().toString();
                String gender = edtGender.getText().toString();

                if (v.getId() == R.id.btnAddData){
                    long rowId = myDatabaseHelper.insertData(name, age, gender);
                    if (rowId == -1){
                        Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Row "+rowId+" is successfully inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

package com.example.shakil.sqlitedemoproject;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakil.sqlitedemoproject.Database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtAge, edtGender, edtId;
    private Button btnAddData, btnShowAllData, btnUpdateData;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);

        btnAddData = findViewById(R.id.btnAddData);
        btnShowAllData = findViewById(R.id.btnShowAllData);
        btnUpdateData = findViewById(R.id.btnUpdateData);

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

        btnShowAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnShowAllData){
                    Cursor cursor = myDatabaseHelper.displayAllData();
                    if (cursor.getCount() == 0){
                        showData("Error", "No data found.");
                        return;
                    }
                    else {
                        StringBuffer stringBuffer = new StringBuffer();
                        while (cursor.moveToNext()){
                            stringBuffer.append("ID : " + cursor.getString(0) + "\n");
                            stringBuffer.append("Name : " + cursor.getString(1) + "\n");
                            stringBuffer.append("Age : " + cursor.getString(2) + "\n");
                            stringBuffer.append("Gender : " + cursor.getString(3) + "\n\n");
                        }
                        showData("ResultSet", stringBuffer.toString());
                    }
                }
            }
        });

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnUpdateData){
                    String name = edtName.getText().toString();
                    String age = edtAge.getText().toString();
                    String gender = edtGender.getText().toString();
                    String id = edtId.getText().toString();

                    Boolean isUpdated = myDatabaseHelper.updatData(id, name, age, gender);

                    if (isUpdated == true){
                        Toast.makeText(MainActivity.this, "Data is successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /*Show All Data*/
    private void showData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}

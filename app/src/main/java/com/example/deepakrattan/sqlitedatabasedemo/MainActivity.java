package com.example.deepakrattan.sqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPassword, edtPhone, edtEmail;
    private String uid, name, password, phone, email;
    private Button btnSave, btnView, btnDelete, btnUpdate;
    private MyDatabase myDatabase;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewBYID
        edtName = (EditText) findViewById(R.id.edtName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnView = (Button) findViewById(R.id.btnView);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        //Registering buttons for click event
        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        myDatabase = new MyDatabase(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                Toast.makeText(MainActivity.this, "Save clicked", Toast.LENGTH_LONG).show();
                name = edtName.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                phone = edtPhone.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                db = myDatabase.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(MyDatabase.NAME, name);
                cv.put(MyDatabase.PASSWORD, password);
                cv.put(MyDatabase.PHONE, phone);
                cv.put(MyDatabase.EMAIL, email);

                long id = db.insert(MyDatabase.TABLE_NAME, null, cv);
                if (id < 0) {
                    Toast.makeText(MainActivity.this, "Insertion Unsuccessful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insertion Successful", Toast.LENGTH_LONG).show();
                    edtName.setText(" ");
                    edtPassword.setText(" ");
                    edtEmail.setText(" ");
                    edtPhone.setText(" ");
                }


                break;
            case R.id.btnView:
                Toast.makeText(MainActivity.this, "View clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getReadableDatabase();
                String[] columns = {MyDatabase.UID, MyDatabase.NAME, MyDatabase.PASSWORD, MyDatabase.PHONE, MyDatabase.EMAIL};
                Cursor cursor = db.query(MyDatabase.TABLE_NAME, columns, null, null, null, null, null);
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    int index1 = cursor.getColumnIndex(MyDatabase.UID);
                    int index2 = cursor.getColumnIndex(MyDatabase.NAME);
                    int index3 = cursor.getColumnIndex(MyDatabase.PASSWORD);
                    int index4 = cursor.getColumnIndex(MyDatabase.PHONE);
                    int index5 = cursor.getColumnIndex(MyDatabase.EMAIL);

                    int uid = cursor.getInt(index1);
                    name = cursor.getString(index2);
                    password = cursor.getString(index3);
                    phone = cursor.getString(index4);
                    email = cursor.getString(index5);

                    buffer.append(uid + " " + name + " " + password + " " + phone + " " + email + "\n");
                }
                Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this, SearchActivity.class));

                break;
            case R.id.btnUpdate:
                Toast.makeText(MainActivity.this, "Update clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                ContentValues cv1 = new ContentValues();
                cv1.put(MyDatabase.NAME, "Deepak Rattan");
                String whereClause1 = MyDatabase.NAME + "=?";
                String[] whereArgs1 = {"Deepak"};
                int u = db.update(MyDatabase.TABLE_NAME, cv1, whereClause1, whereArgs1);
                Toast.makeText(MainActivity.this, "No.of rows updated = " + u, Toast.LENGTH_LONG).show();
                break;
            case R.id.btnDelete:
                Toast.makeText(MainActivity.this, "Delete clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                String whereClause = MyDatabase.NAME + "=?";
                String[] whereArgs = {"Deepak"};
                int d = db.delete(MyDatabase.TABLE_NAME, whereClause, whereArgs);
                Toast.makeText(MainActivity.this, "No. of Rows deleted = " + d, Toast.LENGTH_LONG).show();
                break;
        }
    }
}

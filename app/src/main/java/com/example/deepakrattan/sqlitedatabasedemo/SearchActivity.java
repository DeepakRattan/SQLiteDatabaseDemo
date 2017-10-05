package com.example.deepakrattan.sqlitedatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText edtSearch;
    private Button btnSearch;
    private RecyclerView rv;
    private MyDatabase myDatabase;
    private SQLiteDatabase db;
    private String name, password, phone, email;
    private SingleRow singleRow;
    private ArrayList<SingleRow> singleRowArrayList;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Cursor cursor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //findViewById
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        rv = (RecyclerView) findViewById(R.id.rv);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        myDatabase = new MyDatabase(this);
        db = myDatabase.getWritableDatabase();

        db = myDatabase.getReadableDatabase();
        singleRowArrayList = new ArrayList<>();
        String[] columns = {MyDatabase.UID, MyDatabase.NAME, MyDatabase.PASSWORD, MyDatabase.PHONE, MyDatabase.EMAIL};
        cursor = db.query(MyDatabase.TABLE_NAME, columns, null, null, null, null, null);
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

            singleRow = new SingleRow(name, phone);
            singleRowArrayList.add(singleRow);
        }

        adapter = new MyAdapter(this, singleRowArrayList);
        rv.setAdapter(adapter);

        //To show the search results

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = edtSearch.getText().toString().trim();
                cursor = myDatabase.search(n);
                if (cursor != null) {
                    //if search is successful,empty the ArrayList so that
                    //only the searched data can be viewed
                    singleRowArrayList.clear();
                    while (cursor.moveToNext()) {
                        int index1 = cursor.getColumnIndex(MyDatabase.NAME);
                        int index2 = cursor.getColumnIndex(MyDatabase.PHONE);

                        name = cursor.getString(index1);
                        phone = cursor.getString(index2);
                        singleRow = new SingleRow(name, phone);
                        singleRowArrayList.add(singleRow);
                    }
                    adapter = new MyAdapter(SearchActivity.this, singleRowArrayList);

                    rv.setAdapter(adapter);
                } else {
                    Toast.makeText(SearchActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}

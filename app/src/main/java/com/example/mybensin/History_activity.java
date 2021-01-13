package com.example.mybensin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;

import java.util.ArrayList;

public class History_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper mydb;
    ArrayList<String> id,distance,efisiens,bensin,cosful,nedful;
    customadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);

        mydb=new MyDatabaseHelper(History_activity.this);
        id=new ArrayList<>();
        distance = new ArrayList<>();
        efisiens = new ArrayList<>();
        bensin = new ArrayList<>();
        cosful = new ArrayList<>();
        nedful = new ArrayList<>();
        recyclerView=findViewById(R.id.historyview);

        simpanDatakeArray();
        myadapter=new customadapter(History_activity.this,id,distance,efisiens,bensin,cosful,nedful);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(History_activity.this));
    }

    void simpanDatakeArray()
    {
        Cursor cursor=mydb.readAlldata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                distance.add(cursor.getString(1));
                efisiens.add(cursor.getString(2));
                bensin.add(cursor.getString(3));
                cosful.add(cursor.getString(4));
                nedful.add(cursor.getString(5));
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.the_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==R.id.delete_all)
       {
           Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
           MyDatabaseHelper mydb = new MyDatabaseHelper(this);
           mydb.deleteAllData();
           finish();
           overridePendingTransition(0, 0);
           startActivity(getIntent());
           overridePendingTransition(0, 0);
       }

        return super.onOptionsItemSelected(item);
    }
}
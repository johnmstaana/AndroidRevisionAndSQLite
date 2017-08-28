package com.example.android.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.data.database.DBHelper;
import com.example.android.data.database.DataSource;
import com.example.android.data.model.DataItem;
import com.example.android.data.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    List<DataItem> dataItemList = SampleDataProvider.dataItemList;

    DataSource mDatasource;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDatasource = new DataSource(this);
        mDatasource.open();
        mDatasource.seedDatabase(dataItemList);


        List<DataItem> listFromDB = mDatasource.getAllItems();


        DataItemAdapter adapter = new DataItemAdapter(this, listFromDB);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mDatasource.close();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mDatasource.open();
    }
}

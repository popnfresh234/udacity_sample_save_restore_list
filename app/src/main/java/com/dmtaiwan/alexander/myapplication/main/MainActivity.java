package com.dmtaiwan.alexander.myapplication.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmtaiwan.alexander.myapplication.R;
import com.dmtaiwan.alexander.myapplication.models.MainItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainListener {

    private static final String KEY_DATA = "com.dmtaiwan.alexander.data";


    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private ArrayList<MainItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setup recycler view
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        adapter = new MainAdapter();
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);


        if (savedInstanceState == null) {
            //Get data from AsyncTask if none to restore
            MainAsyncTask asyncTask = new MainAsyncTask(this);
            asyncTask.execute();
        } else {
            //restore data from instance state
            items = savedInstanceState.getParcelableArrayList(KEY_DATA);
            adapter.setData(items);
        }


    }

    @Override
    public void returnResults(ArrayList<MainItem> items) {
        this.items = items;

        adapter.setData(this.items);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_DATA, items);
        super.onSaveInstanceState(outState);
    }
}

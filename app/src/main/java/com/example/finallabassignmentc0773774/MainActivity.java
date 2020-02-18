package com.example.finallabassignmentc0773774;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<UserM> usersL = new ArrayList<>();
    private UserServiceImpl userService;

    private ImageButton addBtn;
    private RecyclerView recyclerView;
    private RecylAdapter recylAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initRecyclerView();
        fetchDataFromDB();
    }


    private void initView() {

        addBtn = findViewById(R.id.add);

        userService = new UserServiceImpl(getBaseContext());

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recylAdapter = new RecylAdapter(usersL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recylAdapter);
    }

    public void fetchDataFromDB() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                usersL.removeAll(usersL);
                usersL.addAll(userService.getAll());
                return null;
            }

            @Override
            protected void onPostExecute(Void agentsCount) {
                recylAdapter.notifyDataSetChanged();
            }
        }.execute();
    }





}

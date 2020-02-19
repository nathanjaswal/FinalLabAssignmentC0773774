package com.example.finallabassignmentc0773774;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<UserM> usersL = new ArrayList<>();
    private UserServiceImpl userService;

    private ImageButton addBtn;
    private RecyclerView recyclerView;
    private RecylAdapter recylAdapter;

    private TextView totalTV;


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
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                startActivityForResult(intent, 0);

            }
        });

        totalTV = findViewById(R.id.total);

        userService = new UserServiceImpl(getApplicationContext());

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
                totalTV.setText(String.valueOf("Total Users: "+usersL.size()));
                recylAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        fetchDataFromDB();
    }
}

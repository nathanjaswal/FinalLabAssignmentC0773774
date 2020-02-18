package com.example.finallabassignmentc0773774;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private Button saveBtn;
    private EditText fnameET;
    private EditText lnameET;
    private EditText addressET;
    private EditText phoneET;

    public UserM existUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {

        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveClicked();
            }
        });


        fnameET = findViewById(R.id.fname);
        lnameET = findViewById(R.id.lname);
        addressET = findViewById(R.id.address);
        phoneET = findViewById(R.id.phone);

    }

    private void saveClicked() {
        Boolean chkV = checkValid();
        if (chkV == true){

        }

    }

    private boolean checkValid(){
        if(fnameET.getText().toString().isEmpty() || lnameET.getText().toString().isEmpty() || addressET.getText().toString().isEmpty() || phoneET.getText().toString().isEmpty()) {
            Toast.makeText(DetailActivity.this,
                    "All Fields are required...", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    private void saveToDB() {
        UserM newUser = null;

        newUser.setFname(fnameET.getText().toString());
        newUser.setLname(lnameET.getText().toString());
        newUser.setAddress(addressET.getText().toString());
        newUser.setPhone(phoneET.getText().toString());

        UserServiceImpl userService = new UserServiceImpl(getBaseContext());

        userService.insertAll(newUser);

    }


}

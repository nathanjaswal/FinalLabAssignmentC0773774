package com.example.finallabassignmentc0773774;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        Intent intent = getIntent();

        //
        Boolean editB = intent.getBooleanExtra("editBool", false);
        if (editB == true) {

            existUser = intent.getParcelableExtra("userm");
            fnameET.setText(existUser.getFname());
            lnameET.setText(existUser.getLname());
            phoneET.setText(existUser.getPhone());
            addressET.setText(existUser.getAddress());

        }

    }

    private void initView() {

        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveClicked(v);
            }
        });


        fnameET = findViewById(R.id.fname);
        lnameET = findViewById(R.id.lname);
        addressET = findViewById(R.id.address);
        phoneET = findViewById(R.id.phone);

    }

    private void saveClicked(View v) {
        Boolean chkV = checkValid();
        if (chkV == true){
            saveToDB(v);
        }else{
            Toast.makeText(DetailActivity.this,
                    "All Fields are required...", Toast.LENGTH_LONG).show();
        }

    }

    private boolean checkValid(){
        if(fnameET.getText().toString().isEmpty() || lnameET.getText().toString().isEmpty() || addressET.getText().toString().isEmpty() || phoneET.getText().toString().isEmpty()) {

            return false;
        }

        return true;
    }


    private void saveToDB(View v) {

        Intent intent = getIntent();

        Boolean editB = intent.getBooleanExtra("editBool", false);
        if (editB == true) {
            UserM newUser = new UserM(fnameET.getText().toString(), lnameET.getText().toString(), phoneET.getText().toString(), addressET.getText().toString());

            UserServiceImpl userService = new UserServiceImpl(getApplicationContext());

            userService.insertAll(newUser);
        }else {
            UserM newUser = new UserM(fnameET.getText().toString(), lnameET.getText().toString(), phoneET.getText().toString(), addressET.getText().toString());

            UserServiceImpl userService = new UserServiceImpl(getApplicationContext());

            userService.update(newUser);
        }

        hideKeyboard(this);

        setResult(RESULT_OK, intent);
        finish();

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


}

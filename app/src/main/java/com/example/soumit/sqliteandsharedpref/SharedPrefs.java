package com.example.soumit.sqliteandsharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends AppCompatActivity {

    private Button saveButton;
    private EditText enterMessage;
    private TextView result;
    private SharedPreferences myPrefs;
    private static final String PREFS_NAME = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        setUpUiComponents();
        fetchDataFromSharedPref("message");

    }

    private void setUpUiComponents() {
        saveButton = (Button) findViewById(R.id.btn_id);
        enterMessage = (EditText) findViewById(R.id.edittext_id);
        result = (TextView) findViewById(R.id.textview_id);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPrefs = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("message", enterMessage.getText().toString());
                editor.commit();
            }
        });
    }

    public void fetchDataFromSharedPref(String key){
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        if(prefs.contains(key)){
            String message = prefs.getString(key, "not found");
            result.setText("Message : " + message);
        }
    }

}




















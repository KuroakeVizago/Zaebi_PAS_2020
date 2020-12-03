package com.example.zaebi_pas_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText txt_Username;
    EditText txt_Password;
    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        pref = getSharedPreferences("Login", MODE_PRIVATE);

        txt_Username = (EditText) findViewById(R.id.txt_Username);
        txt_Password = (EditText) findViewById(R.id.txt_Password);
        btn_Login = (Button) findViewById(R.id.btn_Login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_Username.getText().toString() != "" && txt_Password.getText().toString() != "") {

                    editor = pref.edit();
                    editor.putString("UserId", txt_Username.getText().toString());
                    editor.putString("Status", "LoggedIn");
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                    finish();

                } else if (txt_Username.getText().toString() != "")
                    Toast.makeText(Login.this, "Username and password can't be left empty", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
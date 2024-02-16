package com.example.guessthesound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    private Button bRegister;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bRegister=findViewById(R.id.bRegister);
        etName=findViewById(R.id.etName);
    }

    public void bRegisterOnClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        String nom = etName.getText().toString().trim();
        if (nom.isEmpty()){
            etName.setError("El nom no pot estar buit");
        } else {
            i.putExtra("name", nom);
            startActivity(i);
        }
    }
}
package com.example.guessthesound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
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
        Intent i = new Intent(this, StartActivity.class);
        String nom = etName.getText().toString().trim();
        if (nom.isEmpty()){
            etName.setError("El nom no pot estar buit");
        } else {
            i.putExtra("name", nom);
            startActivity(i);
        }
    }
    /*
    -Mario bross
    -Dark souls
    -Sonic
    -Tetris
    -Pokemon
    -Minecraft
    -The legend of zelda
    -Pacman
    -Wii sports
    -Hollow knight
    -Smash bross
    -Mario kart
    -Overwatch
    -Metal gear
 */
}
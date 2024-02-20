package com.example.guessthesound;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    private ImageView ibFirst, ibSecond, ibThird;
    private ImageButton ibPlay;
    private TextView tvResult, tvPoints, tvBest;

    private final Integer[] images = {R.drawable.darksouls, R.drawable.hollowknight, R.drawable.mario, R.drawable.pacman,
            R.drawable.pokemon, R.drawable.sonic, R.drawable.tetris, R.drawable.wiisports, R.drawable.zelda, R.drawable.metalgear, R.drawable.mariokart,
            R.drawable.minecraft, R.drawable.overwatch, R.drawable.smashbros, R.drawable.cyberpunk, R.drawable.doom, R.drawable.gtasa, R.drawable.halo,
            R.drawable.mortalkombat, R.drawable.portal, R.drawable.skyrim, R.drawable.thelastofus, R.drawable.undertale, R.drawable.wow};
    private final Integer[] audios = {R.raw.darksouls, R.raw.hollowknight, R.raw.mario, R.raw.pacman,
            R.raw.pokemon, R.raw.sonic, R.raw.tetris, R.raw.wiisports, R.raw.zelda, R.raw.metalgear, R.raw.mariokart,
            R.raw.minecraft, R.raw.overwatch, R.raw.smashbros, R.raw.cyberpunk, R.raw.doom, R.raw.gtasa, R.raw.halo, R.raw.mortalkombat, R.raw.portal,
            R.raw.skyrim, R.raw.thelastofus, R.raw.undertale, R.raw.wow};
    private List<Integer> notUsedImages = new LinkedList<Integer>(Arrays.asList(images));
    private List<Integer> notUsedAudios = new LinkedList<Integer>(Arrays.asList(audios));
    private final List<Integer> usedAudios = new LinkedList<Integer>();
    private Integer points = 0;
    private Integer chosenAudio;
    private Integer winnerIndex;
    private MediaPlayer mp;
    private String nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
        Bundle bundle = getIntent().getExtras();
        nombre = bundle.getString("name");
        process();
    }

    private void
    process (){
        tvBest.setVisibility(View.GONE);
        setNewImages();
        chooseWinnerAudio();
        mp = MediaPlayer.create(StartActivity.this, chosenAudio);
        ibPlay.setOnClickListener(v -> playSoundOnClick());
        winnerCases();
    }

    private void setNewImages() {
        setRandomImage(ibFirst);
        setRandomImage(ibSecond);
        setRandomImage(ibThird);
    }

    private void setRandomImage(ImageView iv) {
        Integer randomIndex = new Random().nextInt(notUsedImages.size());
        Integer imageId = notUsedImages.get(randomIndex);
        Integer audioId = notUsedAudios.get(randomIndex);
        usedAudios.add(audioId);
        iv.setImageResource(imageId);
        notUsedImages.remove(imageId);
        notUsedAudios.remove(audioId);
    }

    private void chooseWinnerAudio() {
        winnerIndex = new Random().nextInt(usedAudios.size());
        Integer audioId = usedAudios.get(winnerIndex);
        chosenAudio = audioId;
    }

    private void initializer() {
        ibFirst = findViewById(R.id.ibFirst);
        ibSecond = findViewById(R.id.ibSecond);
        ibThird = findViewById(R.id.ibThird);
        ibPlay = findViewById(R.id.ibPlay);
        tvPoints = findViewById(R.id.tvPoints);
        tvResult = findViewById(R.id.tvResult);
        tvBest = findViewById(R.id.tvBest);
    }

    private void playSoundOnClick() {
        if (mp.isPlaying()) {
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        mp.start();
    }

    private void winnerOnClick(){
        mp.stop();
        points++;
        tvResult.setText("Correct!!");
        tvPoints.setText(points.toString());
        usedAudios.clear();
        notUsedAudios = new LinkedList<Integer>(Arrays.asList(audios));
        notUsedImages = new LinkedList<Integer>(Arrays.asList(images));
        cronometre();
    }
    private void loserOnClick(){
        tvResult.setText("Wrong answer");
        usedAudios.clear();
        ibFirst.setVisibility(View.GONE);
        ibSecond.setVisibility(View.GONE);
        ibThird.setVisibility(View.GONE);
        tvBest.setVisibility(View.VISIBLE);
        topScore();
    }

    public void topScore (){
        SharedPreferences sharedPref = getSharedPreferences("cat.institutmarianao.GostBusters", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String topPlayer = sharedPref.getString("topPlayer", null);
        int topScore = sharedPref.getInt(topPlayer, 0);
        if (points > topScore) {
            editor.putString("topPlayer", nombre);
            tvBest.setText("Maxima puntuacio: " + points + "\nJugador: " + nombre);
        }
        editor.commit();
    }

    private void winnerCases (){
        switch (winnerIndex){
            case 0:
                ibFirst.setOnClickListener(v -> winnerOnClick());
                ibSecond.setOnClickListener(v -> loserOnClick());
                ibThird.setOnClickListener(v -> loserOnClick());
                break;
            case 1:
                ibFirst.setOnClickListener(v -> loserOnClick());
                ibSecond.setOnClickListener(v -> winnerOnClick());
                ibThird.setOnClickListener(v -> loserOnClick());
                break;
            case 2:
                ibFirst.setOnClickListener(v -> loserOnClick());
                ibSecond.setOnClickListener(v -> loserOnClick());
                ibThird.setOnClickListener(v -> winnerOnClick());
        }
    }

    public void cronometre(){
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                process();
            }
        }.start();
    }

}
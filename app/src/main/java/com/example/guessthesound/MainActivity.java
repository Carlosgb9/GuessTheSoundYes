package com.example.guessthesound;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ibFirst, ibSecond, ibThird;
    private ImageButton ibPlay;

    private final Integer[] images = {R.drawable.darksouls, R.drawable.hollowknight, R.drawable.mario, R.drawable.pacman,
            R.drawable.pokemon, R.drawable.sonic, R.drawable.tetris, R.drawable.wiisports, R.drawable.zelda, R.drawable.metalgear, R.drawable.mariokart,
            R.drawable.minecraft, R.drawable.overwatch, R.drawable.smashbros};
    private final Integer[] audios = {R.raw.darksouls, R.raw.hollowknight, R.raw.mario, R.raw.pacman,
            R.raw.pokemon, R.raw.sonic, R.raw.tetris, R.raw.wiisports, R.raw.zelda, R.raw.metalgear, R.raw.mariokart,
            R.raw.minecraft, R.raw.overwatch, R.raw.smashbros};
    private List<Integer> notUsedImages = new LinkedList<Integer>(Arrays.asList(images));
    private final List<Integer> notUsedAudios = new LinkedList<Integer>(Arrays.asList(audios));
    private final List<Integer> usedAudios = new LinkedList<Integer>();
    private final List<Integer> usedImages = new LinkedList<Integer>();

    private Integer chosenAudio;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
        setNewImages();
        chooseRandomAudio();
        mp = MediaPlayer.create(MainActivity.this, chosenAudio);
        ibPlay.setOnClickListener(v -> playSoundOnClick());
    }

    private void setNewImages() {
        setRandomImage(ibFirst);
        setRandomImage(ibSecond);
        setRandomImage(ibThird);
        notUsedImages = Arrays.asList(images);
    }

    private void setRandomImage(ImageView iv) {
        Integer randomIndex = new Random().nextInt(notUsedImages.size());
        Integer imageId = notUsedImages.get(randomIndex);
        Integer audioId = notUsedAudios.get(randomIndex);
        usedAudios.add(audioId);
        usedImages.add(imageId);
        iv.setImageResource(imageId);
        notUsedImages.remove(imageId);
        notUsedAudios.remove(audioId);
    }

    private void chooseRandomAudio() {
        Integer randomIndex = new Random().nextInt(usedAudios.size());
        Integer audioId = usedAudios.get(randomIndex);
        chosenAudio = audioId;
    }

    private void initializer() {
        ibFirst = findViewById(R.id.ibFirst);
        ibSecond = findViewById(R.id.ibSecond);
        ibThird = findViewById(R.id.ibThird);
        ibPlay = findViewById(R.id.ibPlay);
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

    private void imageOnClick() {

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
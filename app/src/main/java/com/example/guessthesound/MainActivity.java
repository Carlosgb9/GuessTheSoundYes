package com.example.guessthesound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ibFirst, ibSecond, ibThird;
    private ImageButton ibPlay;

    private Integer[] images = {R.raw.darksouls, R.raw.hollowknight, R.raw.mario, R.raw.minecraft, R.raw.pacman,
            R.raw.pokemon, R.raw.sonic, R.raw.tetris, R.raw.wiisports, R.raw.zelda};
    private List<Integer> notUsedImages = new LinkedList<Integer>(Arrays.asList(images));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
        setNewImages();
    }

    private void setNewImages(){
        setRandomImage(ibFirst);
        setRandomImage(ibSecond);
        setRandomImage(ibThird);
        notUsedImages = Arrays.asList(images);
    }

    private void setRandomImage(ImageView iv){
        Integer randomIndex = new Random().nextInt(notUsedImages.size());
        Integer imageId = notUsedImages.get(randomIndex);
        iv.setImageResource(imageId);
        notUsedImages.remove(imageId);
    }

    private void initializer (){
        ibFirst = findViewById(R.id.ibFirst);
        ibSecond = findViewById(R.id.ibSecond);
        ibThird = findViewById(R.id.ibThird);
        ibPlay = findViewById(R.id.ibPlay);
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
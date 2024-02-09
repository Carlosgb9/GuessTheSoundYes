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

    private Integer[] images = {R.drawable.darksouls, R.drawable.hollowknight, R.drawable.mario, R.drawable.minecraft, R.drawable.pacman,
            R.drawable.pokemon, R.drawable.sonic, R.drawable.tetris, R.drawable.wiisports, R.drawable.zelda, R.drawable.metalgear, R.drawable.mariokart,
            R.drawable.minecraft, R.drawable.overwatch, R.drawable.smashbros};
    private Integer[] audio = {R.raw.darksouls, R.raw.hollowknight, R.raw.mario, R.raw.minecraft, R.raw.pacman,
            R.raw.pokemon, R.raw.sonic, R.raw.tetris, R.raw.wiisports, R.raw.zelda, R.raw.metalgear, R.raw.mariokart,
            R.raw.minecraft, R.raw.overwatch, R.raw.smashbros};
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
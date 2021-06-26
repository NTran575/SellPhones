package com.dynamicdevz.sellphones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.dynamicdevz.sellphones.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //startbutton
        binding.startButton.setOnClickListener(view-> {
            Intent intent = new Intent(this, SecondPageActivity.class);
            startActivity(intent);
        });

    }
}
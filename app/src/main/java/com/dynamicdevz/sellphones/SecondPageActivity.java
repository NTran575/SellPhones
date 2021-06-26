package com.dynamicdevz.sellphones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dynamicdevz.sellphones.databinding.ActivitySecondPageBinding;

public class SecondPageActivity extends AppCompatActivity {

    private ActivitySecondPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySecondPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addButton.setOnClickListener(view-> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        //binding.deleteButton.setOnClickListener(view-> {
        //    Intent intent = new Intent(this, -----.class);
        //    startActivity(intent);
        //});

        //back to home page
        binding.homeButton.setOnClickListener(view-> {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
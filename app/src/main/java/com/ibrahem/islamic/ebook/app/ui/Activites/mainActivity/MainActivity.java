package com.ibrahem.islamic.ebook.app.ui.Activites.mainActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ibrahem.islamic.ebook.R;
import ibrahem.islamic.ebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }
}

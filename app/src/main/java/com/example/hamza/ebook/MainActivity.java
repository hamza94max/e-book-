package com.example.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class MainActivity extends AppCompatActivity {

    int request ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bio(View view) {

        Intent intent=new Intent(getApplicationContext(),Bio.class);
        startActivity(intent);

    }

    public void open(View view) {
        Intent i =new Intent(getApplicationContext(),Read.class);
        request=1;
        i.putExtra("EXTRA_SESSION_ID", request);
        startActivity(i);




    }

    public void openway(View view) {
        Intent i =new Intent(getApplicationContext(),Read.class);
        request=2;
        i.putExtra("EXTRA_SESSION_ID", request);
        startActivity(i);
    }

    public void open_raqaq(View view) {
        Intent i =new Intent(getApplicationContext(),Read.class);
        request=3;
        i.putExtra("EXTRA_SESSION_ID", request);
        startActivity(i);
    }
}
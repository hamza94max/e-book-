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

    public void openpdf(int requestt){
        Intent intent =new Intent(getApplicationContext(),Read.class);
        request=requestt;
        intent.putExtra("EXTRA_SESSION_ID",request);
        startActivity(intent);
    }

    public void open(View view) { openpdf(1); }

    public void openway(View view) {
        openpdf(2);
    }

    public void open_raqaq(View view) {
        openpdf(3);
    }

    public void open_masl(View view) {
        openpdf(4);
    }

    public void open_sulta(View view) {
        openpdf(5);
    }

    public void open_tawel(View view) {
        openpdf(6);
    }

    public void open_mag(View view) {
        openpdf(7);
    }
}
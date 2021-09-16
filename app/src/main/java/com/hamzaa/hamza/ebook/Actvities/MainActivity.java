package com.hamzaa.hamza.ebook.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.BuildConfig;
import com.hamzaa.hamza.ebook.Fragment.BioFragment;
import com.hamzaa.hamza.ebook.R;
import com.hamzaa.hamza.ebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int request ;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


    }

    /*public void OpenBio(View view) {
        openFragmentFull();
    }

    void openFragmentFull() {
        getSupportFragmentManager().beginTransaction().add(R.id.container,new BioFragment()).commit();
    }*/

    public void openpdf(int requestt){
        Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
        request = requestt;
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

    public void shareApp(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            Toast.makeText( getBaseContext(), "خلل في مشاركة التطبيق، المرجو الإعادة", Toast.LENGTH_SHORT);
        }
    }



}

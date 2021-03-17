package com.example.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class Read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfViewe);




        int BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);

        if (BookId==1){
            pdfView.fromAsset(("malat.pdf")).load();
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        }else if (BookId==2){
            pdfView.fromAsset(("way.pdf")).load();
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        }else if (BookId==3){
            pdfView.fromAsset(("raq.pdf")).load();
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        }






    }
}
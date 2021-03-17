package com.example.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;

public class Read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfViewe);
        int BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);


        switch (BookId){
            case 1: pdfView.fromAsset(("malat.pdf")).load();
            case 2: pdfView.fromAsset(("way.pdf")).load();
            case 3: pdfView.fromAsset(("raq.pdf")).load();
            case 4: pdfView.fromAsset(("masl.pdf")).load();
            case 5: pdfView.fromAsset(("sulta.pdf")).load();
            case 6: pdfView.fromAsset(("tawel.pdf")).load();
            case 7: pdfView.fromAsset(("mag.pdf")).load(); }


    }
}
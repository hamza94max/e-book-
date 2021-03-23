package com.example.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class Read extends AppCompatActivity implements OnPageChangeListener {
    int pageNumber =0;
    private String pdfFileName;
    int savepage;
    PDFView pdfView;
    private String PDF_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

         pdfView = findViewById(R.id.pdfViewe);


        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);

        savepage = mySharedPreferences.getInt("retrievedPage",0);
        pageNumber = savepage;

        int BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);

        switch (BookId){

            case 1:
                pdfFileName ="مآلات الخطاب المدني";
                PDF_FILE="malat.pdf";
                displayFromAsset(PDF_FILE);
            break;
            
            case 2:
                pdfFileName ="الطريق إلى القرآن";
                PDF_FILE="way.pdf";
                displayFromAsset(PDF_FILE);
            break;

            case 3:
                pdfFileName ="رقائق القرآن";
                PDF_FILE="raq.pdf";
                displayFromAsset(PDF_FILE);
            break;

            case 4:
                pdfFileName ="مسلكيات";
                PDF_FILE="masl.pdf";
                displayFromAsset(PDF_FILE);
            break;

            case 5:
                pdfFileName ="سُلطة الثقافة الغالِبة";
                PDF_FILE="sulta.pdf";
                displayFromAsset(PDF_FILE);
            break;

            case 6:
                pdfFileName ="التأويل الحداثي للتراث";
                PDF_FILE="tawel.pdf";
                displayFromAsset(PDF_FILE);
            break;

            case 7:
                pdfFileName ="الماجريات";
                PDF_FILE="mag.pdf";
                displayFromAsset(PDF_FILE);
            break;
        }
    }



    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;


        pdfView.fromAsset(PDF_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }
}
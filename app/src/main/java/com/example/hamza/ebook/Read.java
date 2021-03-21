package com.example.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class Read extends AppCompatActivity implements OnPageChangeListener {
    int pageNumber =0;
    private String pdfFileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfViewe);




        int BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);
        if (BookId==1){

            SharedPreferences prefs = getSharedPreferences("pager", MODE_PRIVATE);
            int malat = prefs.getInt("page", 0); //0 is the default value.

            pdfFileName ="مآلات الخطاب المدني";
            pdfView.fromAsset(("malat.pdf"))
                    .onPageChange(this)
                    .defaultPage(malat)
                    .enableAnnotationRendering(true)
                    .spacing(2)
                    .load();
            Toast.makeText(this, "توقفت عند "+ " "+(malat+1), Toast.LENGTH_SHORT).show();



        }else if (BookId==2){
            pdfFileName ="الطريق إلى القرآن";
            SharedPreferences prefs = getSharedPreferences("pager", MODE_PRIVATE);
            int pageid = prefs.getInt("way", 0); //0 is the default value.
            pdfView.fromAsset(("way.pdf")).onPageChange(this)
                    .defaultPage(0)
                    .enableAnnotationRendering(true)
                    .spacing(2)
                    .load();
           // Toast.makeText(this, "توقفت عند "+ " "+(pageid+1), Toast.LENGTH_SHORT).show();

        }else if (BookId==3){
            pdfFileName ="رقائق القرآن";
            pdfView.fromAsset(("raq.pdf")).onPageChange(this).load();
        }else if (BookId==4){
            pdfFileName ="مسلكيات";
            pdfView.fromAsset(("masl.pdf")).onPageChange(this).load();
        }else if (BookId==5){
            pdfFileName ="سُلطة الثقافة الغالِبة";
            pdfView.fromAsset(("sulta.pdf")).onPageChange(this).load();
        }else if (BookId==6){
            pdfFileName ="التأويل الحداثي للتراث";
            pdfView.fromAsset(("tawel.pdf")).onPageChange(this).load();
        }
        else if (BookId==7){
            pdfFileName ="الماجريات";
            pdfView.fromAsset(("mag.pdf")).onPageChange(this).load();
        }






    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));


        SharedPreferences.Editor editor = getSharedPreferences("pager", MODE_PRIVATE).edit();
        editor.putInt("page", pageNumber);
        editor.apply();

    }
}
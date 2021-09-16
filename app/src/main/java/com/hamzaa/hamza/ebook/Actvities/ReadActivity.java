package com.hamzaa.hamza.ebook.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.hamzaa.hamza.ebook.R;
import com.hamzaa.hamza.ebook.databinding.ActivityReadBinding;

public class ReadActivity extends AppCompatActivity implements OnPageChangeListener {

    private String pdfFileName ;
    int [] Booksarray ;
    int [] LastOpenedPagearray ;
    int BookId ;

    ActivityReadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_read);

        Booksarray = new int[] {0,0,0,0,0,0,0} ;
        LastOpenedPagearray = new int [] {0,0,0,0,0,0,0} ;

        SharedPreferences mySharedPreferences = getSharedPreferences("shared",Context.MODE_PRIVATE);

        for (int i=0;i<7;i++){
            LastOpenedPagearray[i] = mySharedPreferences.getInt("id"+(i+1),0);
            Booksarray[i] = LastOpenedPagearray[i];
        }

        BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);

        switch (BookId){

            case 1:
                pdfFileName = "مآلات الخطاب المدني";
                String assetFileName = "malat.pdf";
                displayFromAsset(assetFileName,Booksarray[0]);
            break;

            case 2:
                pdfFileName = "الطريق إلى القرآن";
                assetFileName = "way.pdf";
                displayFromAsset(assetFileName,Booksarray[1]);
            break;

            case 3:
                pdfFileName = "رقائق القرآن"  ;
                assetFileName = "raq.pdf";
                displayFromAsset(assetFileName,Booksarray[2]);
            break;

            case 4:
                pdfFileName ="مسلكيات";
                assetFileName = "masl.pdf";
                displayFromAsset(assetFileName,Booksarray[3]);
            break;

            case 5:
                pdfFileName = "سُلطة الثقافة الغالِبة";
                assetFileName = "sulta.pdf";
                displayFromAsset(assetFileName,Booksarray[4]);
            break;

            case 6:
                pdfFileName = "التأويل الحداثي للتراث" ;
                assetFileName = "tawel.pdf";
                displayFromAsset(assetFileName,Booksarray[5]);
            break;

            case 7:
                pdfFileName = "الماجريات";
                assetFileName = "mag.pdf";
                displayFromAsset(assetFileName,Booksarray[6]);
            break;
        }
    }



    private void displayFromAsset(String assetFileName ,int arrid) {
        binding.pdfViewer.fromAsset(assetFileName)
                .defaultPage(arrid)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .load();

        Toast.makeText(this, "توقفت عند "+ ( arrid + 1 ) , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();

        if (page==pageCount)
            Toast.makeText(this, "انتهيت من الكتاب", Toast.LENGTH_SHORT).show();

        switch(BookId){
            case 1:
                Booksarray[0] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id1", Booksarray[0]);
                editor.apply();
                break;

            case 2:
                Booksarray[1] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id2", Booksarray[1]);
                editor.apply();
                break;
            case 3:
                Booksarray[2] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id3", Booksarray[2]);
                editor.apply();
                break;
            case 4:
                Booksarray[3] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id4", Booksarray[3]);
                editor.apply();
                break;
            case 5:
                Booksarray[4] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id5", Booksarray[4]);
                editor.apply();
                break;
            case 6:
                Booksarray[5] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id6", Booksarray[5]);
                editor.apply();
                break;
            case 7:
                Booksarray[6] = page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id7", Booksarray[6]);
                editor.apply();
                break;
        }
    }
}
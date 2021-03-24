package com.example.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class Read extends AppCompatActivity implements OnPageChangeListener {

    private String pdfFileName;
    PDFView pdfView;
    private String PDF_FILE;
    int [] arr;
    int []save;
    int BookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

         pdfView = findViewById(R.id.pdfViewe);



        arr = new int[]{0,0,0,0,0,0,0};
        save=new int []{0,0,0,0,0,0,0};
        SharedPreferences mySharedPreferences = getSharedPreferences("shared",Context.MODE_PRIVATE);

        save[0] = mySharedPreferences.getInt("id1",0);
        save[1] = mySharedPreferences.getInt("id2",0);
        save[2] = mySharedPreferences.getInt("id3",0);
        save[3] = mySharedPreferences.getInt("id4",0);
        save[4] = mySharedPreferences.getInt("id5",0);
        save[5] = mySharedPreferences.getInt("id6",0);
        save[6] = mySharedPreferences.getInt("id7",0);

        arr[0]=save[0];
        arr[1]=save[1];
        arr[2]=save[2];
        arr[3]=save[3];
        arr[4]=save[4];
        arr[5]=save[5];
        arr[6]=save[6];





        BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);

        switch (BookId){

            case 1:
                pdfFileName ="مآلات الخطاب المدني";
                PDF_FILE="malat.pdf";
                displayFromAsset(PDF_FILE,arr[0]);
            break;

            case 2:
                pdfFileName ="الطريق إلى القرآن";
                PDF_FILE="way.pdf";
                displayFromAsset(PDF_FILE,arr[1]);
            break;

            case 3:
                pdfFileName ="رقائق القرآن";
                PDF_FILE="raq.pdf";
                displayFromAsset(PDF_FILE,arr[2]);
            break;

            case 4:
                pdfFileName ="مسلكيات";
                PDF_FILE="masl.pdf";
                displayFromAsset(PDF_FILE,arr[3]);
            break;

            case 5:
                pdfFileName ="سُلطة الثقافة الغالِبة";
                PDF_FILE="sulta.pdf";
                displayFromAsset(PDF_FILE,arr[4]);
            break;

            case 6:
                pdfFileName ="التأويل الحداثي للتراث";
                PDF_FILE="tawel.pdf";
                displayFromAsset(PDF_FILE,arr[5]);
            break;

            case 7:
                pdfFileName ="الماجريات";
                PDF_FILE="mag.pdf";
                displayFromAsset(PDF_FILE,arr[6]);
            break;
        }
    }



    private void displayFromAsset(String assetFileName ,int arrid) {
        

        pdfView.fromAsset(PDF_FILE)
                .defaultPage(arrid)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();
        switch(BookId){
            case 1:
                arr[0]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id1", arr[0]);
                editor.apply();
                break;

            case 2:
                arr[1]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id2", arr[1]);
                editor.apply();
                break;
            case 3:
                arr[2]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id3", arr[2]);
                editor.apply();
                break;
            case 4:
                arr[3]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id4", arr[3]);
                editor.apply();
                break;
            case 5:
                arr[4]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id5", arr[4]);
                editor.apply();
                break;
            case 6:
                arr[5]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id6", arr[5]);
                editor.apply();
                break;
            case 7:
                arr[6]=page;
                setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
                editor.putInt("id7", arr[6]);
                editor.apply();
                break;

        }


    }
}
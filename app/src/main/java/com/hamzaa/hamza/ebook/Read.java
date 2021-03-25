package com.hamzaa.hamza.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.hamzaa.hamza.ebook.databinding.ActivityMainBinding;
import com.hamzaa.hamza.ebook.databinding.ActivityReadBinding;

public class Read extends AppCompatActivity implements OnPageChangeListener {

    private String pdfFileName;
    PDFView pdfView;
    private String assetFileName;
    int [] arr;
    int []save;
    int BookId;

    ActivityReadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


         binding = DataBindingUtil.setContentView(this,R.layout.activity_read);





        arr = new int[]{0,0,0,0,0,0,0};
        save= new int []{0,0,0,0,0,0,0};
        SharedPreferences mySharedPreferences = getSharedPreferences("shared",Context.MODE_PRIVATE);

        for (int i=0;i<7;i++){
            save[i]=mySharedPreferences.getInt("id"+(i+1),0);
            arr[i]=save[i];
        }

        BookId = getIntent().getIntExtra("EXTRA_SESSION_ID",0);

        switch (BookId){

            case 1:
                pdfFileName ="مآلات الخطاب المدني";
                assetFileName ="malat.pdf";
                displayFromAsset(assetFileName,arr[0]);
            break;

            case 2:
                pdfFileName ="الطريق إلى القرآن";
                assetFileName="way.pdf";
                displayFromAsset(assetFileName,arr[1]);
            break;

            case 3:
                pdfFileName ="رقائق القرآن";
                assetFileName="raq.pdf";
                displayFromAsset(assetFileName,arr[2]);
            break;

            case 4:
                pdfFileName ="مسلكيات";
                assetFileName="masl.pdf";
                displayFromAsset(assetFileName,arr[3]);
            break;

            case 5:
                pdfFileName ="سُلطة الثقافة الغالِبة";
                assetFileName="sulta.pdf";
                displayFromAsset(assetFileName,arr[4]);
            break;

            case 6:
                pdfFileName ="التأويل الحداثي للتراث";
                assetFileName="tawel.pdf";
                displayFromAsset(assetFileName,arr[5]);
            break;

            case 7:
                pdfFileName ="الماجريات";
                assetFileName="mag.pdf";
                displayFromAsset(assetFileName,arr[6]);
            break;
        }
    }



    private void displayFromAsset(String assetFileName ,int arrid) {
        binding.pdfViewe.fromAsset(assetFileName)
                .defaultPage(arrid)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .load();

        Toast.makeText(this, "توقفت عند "+(arrid+1), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();

        if (page==pageCount)
            Toast.makeText(this, "انتهيت من الكتاب", Toast.LENGTH_SHORT).show();

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
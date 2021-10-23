package com.hamzaa.hamza.ebook.Actvities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.hamzaa.hamza.ebook.R;
import com.hamzaa.hamza.ebook.databinding.ActivityReadBinding;

public class ReadActivity extends AppCompatActivity implements OnPageChangeListener {

    String pdfFileName, assetFileName;
    int[] Booksarray, LastOpenedPagearray;
    String[] assetFilesName, pdfFileNames, BooksId;
    int BookId;

    ActivityReadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_read);

        Booksarray = new int[7];
        LastOpenedPagearray = new int[7];

        assetFilesName = new String[]{"malat.pdf", "way.pdf", "raq.pdf", "masl.pdf", "sulta.pdf", "tawel.pdf", "mag.pdf"};

        pdfFileNames = new String[]{getString(R.string.MalatBook), getString(R.string.wayToquranBook),
                getString(R.string.RaqaqBook), getString(R.string.MaslakeatBook), getString(R.string.SoltaBook),
                getString(R.string.TawelBook), getString(R.string.MagariatBook)};

        BooksId = new String[]{"id1", "id2", "id3", "id4", "id5", "id6", "id7"};

        SharedPreferences mySharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
        for (int i = 0; i < 7; i++) {
            LastOpenedPagearray[i] = mySharedPreferences.getInt("id" + (i + 1), 0);
            Booksarray[i] = LastOpenedPagearray[i];
        }

        BookId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);

        switch (BookId) {
            case 1:
                putBookdetails(1);
                break;

            case 2:
                putBookdetails(2);
                break;

            case 3:
                putBookdetails(3);
                break;

            case 4:
                putBookdetails(4);
            break;

            case 5:
                putBookdetails(5);
            break;

            case 6:
                putBookdetails(6);
                break;

            case 7:
                putBookdetails(7);
                break;
        }
    }

    private void putBookdetails(int i) {
        pdfFileName = pdfFileNames[i - 1];
        assetFileName = assetFilesName[i - 1];
        displayFromAsset(assetFileName, Booksarray[i - 1]);
    }

    private void displayFromAsset(String assetFileName, int arrid) {
        binding.pdfViewer.fromAsset(assetFileName)
                .defaultPage(arrid)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .load();

        Toast.makeText(this, " توقفت عند " + (arrid + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();

        if (page == pageCount)
            Toast.makeText(this, getString(R.string.finishBook), Toast.LENGTH_SHORT).show();

        switch (BookId) {
            case 1:
                putLastOpenedpage(1, editor, page, pageCount);
                break;
            case 2:
                putLastOpenedpage(2, editor, page, pageCount);
                break;
            case 3:
                putLastOpenedpage(3, editor, page, pageCount);
                break;
            case 4:
                putLastOpenedpage(4, editor, page, pageCount);
                break;
            case 5:
                putLastOpenedpage(5, editor, page, pageCount);
                break;
            case 6:
                putLastOpenedpage(6, editor, page, pageCount);
                break;
            case 7:
                putLastOpenedpage(7, editor, page, pageCount);
                break;
        }
    }

    private void putLastOpenedpage(int i, SharedPreferences.Editor editor, int currentpage, int BookpagesCount) {
        Booksarray[i - 1] = currentpage;
        setTitle(String.format("%s %s / %s", pdfFileName, currentpage + 1, BookpagesCount));
        editor.putInt(BooksId[i], Booksarray[i - 1]);
        editor.apply();
    }
}
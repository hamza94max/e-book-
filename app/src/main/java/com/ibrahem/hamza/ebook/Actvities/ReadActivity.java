package com.ibrahem.hamza.ebook.Actvities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.ibrahem.hamza.ebook.DataSets.PdfFiles;

import ibrahem.hamza.ebook.R;
import ibrahem.hamza.ebook.databinding.ActivityReadBinding;


public class ReadActivity extends AppCompatActivity implements OnPageChangeListener {

    String pdfFileName, assetFileName;
    int[] Defaultpagearray, LastOpenedPagearray;
    String[] assetFilesName, pdfFileNames;
    int BookId;

    ActivityReadBinding binding;

    SharedPreferences LastopenedpageSharedpreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_read);

        Defaultpagearray = new int[]{0, 0, 0, 0, 0, 0, 0};
        LastOpenedPagearray = new int[]{0, 0, 0, 0, 0, 0, 0};

        assetFilesName = new String[]{PdfFiles.MalatPdf.toString(), PdfFiles.WaytoQuranPdf.toString(), PdfFiles.RaqaqPdf.toString(), PdfFiles.MaslakeatPdf.toString(), PdfFiles.SultaPdf.toString(), PdfFiles.TwaelPdf.toString(), PdfFiles.MagraiatPdf.toString()};

        pdfFileNames = new String[]{getString(R.string.MalatBook), getString(R.string.wayToquranBook),
                getString(R.string.RaqaqBook), getString(R.string.MaslakeatBook), getString(R.string.SoltaBook),
                getString(R.string.TawelBook), getString(R.string.MagariatBook)};


        LastopenedpageSharedpreference = getSharedPreferences("shared", Context.MODE_PRIVATE);

        getLastopenedpageforAllBooks();

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

    private void getLastopenedpageforAllBooks() {
        for (int i = 0; i < 7; i++) {
            LastOpenedPagearray[i] = LastopenedpageSharedpreference.getInt("id" + (i + 1), 0);
            Defaultpagearray[i] = LastOpenedPagearray[i];
        }
    }


    private void putBookdetails(int i) {
        pdfFileName = pdfFileNames[i - 1];
        assetFileName = assetFilesName[i - 1];
        displayFromAsset(assetFileName, LastOpenedPagearray[i - 1]);
    }

    private void displayFromAsset(String assetFileName, int page) {
        binding.pdfViewer.fromAsset(assetFileName)
                .defaultPage(page)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .load();

        Toast.makeText(this, " توقفت عند " + (page + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();

        if (page == pageCount)
            Toast.makeText(this, "انتهيت من الكتاب", Toast.LENGTH_SHORT).show();

        switch (BookId) {
            case 1:
                putLastOpenedpageForBook(1, editor, page, pageCount);
                break;

            case 2:
                putLastOpenedpageForBook(2, editor, page, pageCount);
                break;

            case 3:
                putLastOpenedpageForBook(3, editor, page, pageCount);
                break;

            case 4:
                putLastOpenedpageForBook(4, editor, page, pageCount);
                break;

            case 5:
                putLastOpenedpageForBook(5, editor, page, pageCount);
                break;

            case 6:
                putLastOpenedpageForBook(6, editor, page, pageCount);
                break;

            case 7:
                putLastOpenedpageForBook(7, editor, page, pageCount);
                break;
        }
    }

    private void putLastOpenedpageForBook(int i, SharedPreferences.Editor editor, int currentpage, int BookpagesCount) {
        Defaultpagearray[i - 1] = currentpage;
        setTitle(String.format("%s %s / %s", pdfFileName, currentpage + 1, BookpagesCount));
        editor.putInt("id" + i, Defaultpagearray[i - 1]);
        editor.apply();
    }
}
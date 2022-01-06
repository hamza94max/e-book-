package com.ibrahem.hamza.ebook.Actvities.ReadActivity;

import static com.ibrahem.hamza.ebook.Actvities.ReadActivity.Funcations.BookDisplay.displayFromAsset;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.ibrahem.hamza.ebook.Actvities.ReadActivity.Funcations.PdfFiles;
import com.ibrahem.hamza.ebook.Actvities.ReadActivity.Model.BookModel;

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

        assetFilesName = new String[]{PdfFiles.MalatPdf.toString(), PdfFiles.WaytoQuranPdf.toString(),
                PdfFiles.RaqaqPdf.toString(), PdfFiles.MaslakeatPdf.toString(),
                PdfFiles.SultaPdf.toString(), PdfFiles.TwaelPdf.toString(),
                PdfFiles.MagraiatPdf.toString()};

        putBooksname();

        LastopenedpageSharedpreference = getSharedPreferences("shared", Context.MODE_PRIVATE);

        getLastopenedpageforAllBooks();

        BookId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);

        openBook(BookId);

    }

    private void putBooksname() {
        pdfFileNames = new String[]{getString(R.string.MalatBook), getString(R.string.wayToquranBook),
                getString(R.string.RaqaqBook), getString(R.string.MaslakeatBook), getString(R.string.SoltaBook),
                getString(R.string.TawelBook), getString(R.string.MagariatBook)};
    }

    private void getLastopenedpageforAllBooks() {
        for (int i = 0; i < 7; i++) {
            LastOpenedPagearray[i] = LastopenedpageSharedpreference.getInt("id" + (i + 1), 0);
            Defaultpagearray[i] = LastOpenedPagearray[i];
        }
    }

    private void openBook(int bookId) {
        pdfFileName = pdfFileNames[bookId - 1];
        assetFileName = assetFilesName[bookId - 1];
        BookModel book = new BookModel(assetFileName, LastOpenedPagearray[bookId - 1]);
        displayFromAsset(book, binding, this);
        Toast.makeText(this, " توقفت عند " + (book.getLastopenedpage() + 1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();

        putLastOpenedpageForBook(BookId, editor, page, pageCount);
    }

    private void putLastOpenedpageForBook(int Bookid, SharedPreferences.Editor editor, int currentpage, int BookpagesCount) {
        Defaultpagearray[Bookid - 1] = currentpage;
        setTitle(String.format("%s %s / %s", pdfFileName, currentpage + 1, BookpagesCount));
        saveLastopenedpageInSharedpreference(Bookid, editor);
    }

    private void saveLastopenedpageInSharedpreference(int bookid, SharedPreferences.Editor editor) {
        editor.putInt("id" + bookid, Defaultpagearray[bookid - 1]);
        editor.apply();
    }

}
package com.ibrahem.islamic.ebook.app.utill;

import android.content.Context;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.ibrahem.islamic.ebook.app.Data.Model.BookModel;

import ibrahem.islamic.ebook.databinding.ActivityReadBinding;

public class BookDisplay {

    public static void displayFromAsset(BookModel book, ActivityReadBinding binding, Context context) {
        binding.pdfViewer.fromAsset(book.getAssetFileName())
                .defaultPage(book.getLastopenedpage())
                .enableSwipe(true)
                .onPageChange((OnPageChangeListener) context)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .load();
    }
}

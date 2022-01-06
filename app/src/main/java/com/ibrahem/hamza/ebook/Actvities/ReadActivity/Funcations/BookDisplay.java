package com.ibrahem.hamza.ebook.Actvities.ReadActivity.Funcations;

import android.content.Context;

import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.ibrahem.hamza.ebook.Actvities.ReadActivity.Model.BookModel;

import ibrahem.hamza.ebook.databinding.ActivityReadBinding;

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

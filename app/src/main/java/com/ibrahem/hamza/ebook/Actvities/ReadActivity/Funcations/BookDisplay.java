package com.ibrahem.hamza.ebook.Actvities.ReadActivity.Funcations;

import com.ibrahem.hamza.ebook.Actvities.ReadActivity.Model.BookModel;

import ibrahem.hamza.ebook.databinding.ActivityReadBinding;

public class BookDisplay {

    public static void displayFromAsset(BookModel book, ActivityReadBinding binding) {
        binding.pdfViewer.fromAsset(book.getAssetFileName())
                .defaultPage(book.getLastopenedpage())
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .load();
    }
}

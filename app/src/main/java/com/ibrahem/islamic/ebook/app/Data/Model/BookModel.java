package com.ibrahem.islamic.ebook.app.Data.Model;

public class BookModel {
    String assetFileName;
    int lastopenedpage;

    public BookModel(String assetFileName, int lastopenedpage) {
        this.assetFileName = assetFileName;
        this.lastopenedpage = lastopenedpage;
    }

    public String getAssetFileName() {
        return assetFileName;
    }

    public int getLastopenedpage() {
        return lastopenedpage;
    }
}

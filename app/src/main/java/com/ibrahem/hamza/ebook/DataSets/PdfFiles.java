package com.ibrahem.hamza.ebook.DataSets;


import androidx.annotation.NonNull;

public enum PdfFiles {

    MagraiatPdf, MaslakeatPdf, SultaPdf, RaqaqPdf, WaytoQuranPdf, TwaelPdf, MalatPdf;


    @NonNull
    @Override
    public String toString() {
        switch (this) {
            case MalatPdf:
                return "malat.pdf";
            case WaytoQuranPdf:
                return "way.pdf";
            case RaqaqPdf:
                return "raq.pdf";
            case MaslakeatPdf:
                return "masl.pdf";
            case SultaPdf:
                return "sulta.pdf";
            case TwaelPdf:
                return "tawel.pdf";
            case MagraiatPdf:
                return "mag.pdf";
            default:
                return "";
        }
    }
}

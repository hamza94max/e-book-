package com.ibrahem.islamic.ebook.app.ui.Fragments.ReadFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.ibrahem.islamic.ebook.app.Data.Model.BookModel
import com.ibrahem.islamic.ebook.app.Data.PdfFiles
import ibrahem.islamic.ebook.R
import ibrahem.islamic.ebook.databinding.FragmentReadBinding


class ReadFragment : Fragment(), OnPageChangeListener {

    private lateinit var pdfFileName: String
    private lateinit var assetFileName: String
    lateinit var Defaultpagearray: IntArray
    lateinit var LastOpenedPagearray: IntArray
    lateinit var assetFilesName: Array<String>
    lateinit var pdfFileNames: Array<String?>

    private lateinit var LastopenedpageSharedpreference: SharedPreferences

    private var _binding: FragmentReadBinding? = null
    private val binding get() = _binding!!

    private val args: ReadFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReadBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Defaultpagearray = intArrayOf(0, 0, 0, 0, 0, 0, 0)
        LastOpenedPagearray = intArrayOf(0, 0, 0, 0, 0, 0, 0)

        assetFilesName = arrayOf(
            PdfFiles.MalatPdf.toString(), PdfFiles.WaytoQuranPdf.toString(),
            PdfFiles.RaqaqPdf.toString(), PdfFiles.MaslakeatPdf.toString(),
            PdfFiles.SultaPdf.toString(), PdfFiles.TwaelPdf.toString(),
            PdfFiles.MagraiatPdf.toString()
        )

        putBooksname()
        LastopenedpageSharedpreference =
            activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)!!

        getLastopenedpageforAllBooks()



        openBook(args.bookId)

    }


    private fun putBooksname() {
        pdfFileNames = arrayOf(
            getString(R.string.MalatBook),
            getString(R.string.wayToquranBook),
            getString(R.string.RaqaqBook),
            getString(R.string.MaslakeatBook),
            getString(R.string.SoltaBook),
            getString(R.string.TawelBook),
            getString(R.string.MagariatBook)
        )
    }

    private fun getLastopenedpageforAllBooks() {
        for (i in 0..6) {
            LastOpenedPagearray[i] = LastopenedpageSharedpreference.getInt("id" + (i + 1), 0)
            Defaultpagearray[i] = LastOpenedPagearray[i]
        }
    }

    private fun openBook(bookId: Int) {
        pdfFileName = pdfFileNames[bookId - 1]!!
        assetFileName = assetFilesName[bookId - 1]
        val book = BookModel(assetFileName, LastOpenedPagearray[bookId - 1])
        displayFromAsset(book)
        Toast.makeText(context, " توقفت عند " + (book.lastopenedpage + 1), Toast.LENGTH_LONG).show()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        val editor: SharedPreferences.Editor =
            activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)!!.edit()
        putLastOpenedpageForBook(args.bookId, editor, page, pageCount)
    }

    private fun putLastOpenedpageForBook(
        Bookid: Int,
        editor: SharedPreferences.Editor,
        currentpage: Int,
        BookpagesCount: Int
    ) {
        Defaultpagearray[Bookid - 1] = currentpage

        activity?.title = java.lang.String.format(
            "%s %s / %s",
            pdfFileName,
            currentpage + 1,
            BookpagesCount
        )
        saveLastopenedpageInSharedpreference(Bookid, editor)
    }

    private fun saveLastopenedpageInSharedpreference(
        bookid: Int,
        editor: SharedPreferences.Editor
    ) {
        editor.putInt("id$bookid", Defaultpagearray[bookid - 1])
        editor.apply()
    }


    fun displayFromAsset(book: BookModel) {
        binding.pdfViewer.fromAsset(book.assetFileName)
            .defaultPage(book.lastopenedpage)
            .enableSwipe(true)
            .onPageChange(this as OnPageChangeListener?)
            .swipeHorizontal(false)
            .enableAnnotationRendering(true)
            .load()
    }


}
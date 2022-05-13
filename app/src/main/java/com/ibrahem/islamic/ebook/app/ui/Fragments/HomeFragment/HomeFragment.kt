package com.ibrahem.islamic.ebook.app.ui.Fragments.HomeFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.barteksc.pdfviewer.BuildConfig
import ibrahem.islamic.ebook.R
import ibrahem.islamic.ebook.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bioimg.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToBioFragment()
            findNavController().navigate(action)
        }

        initViews()


    }

    private fun initViews() {
        binding.malat.setOnClickListener { openBook(1) }
        binding.wayToQuran.setOnClickListener { openBook(2) }
        binding.raqaqBook.setOnClickListener { openBook(3) }
        binding.maslakeatBook.setOnClickListener { openBook(4) }
        binding.SultaBook.setOnClickListener { openBook(5) }
        binding.TawelBook.setOnClickListener { openBook(6) }
        binding.magariatBook.setOnClickListener { openBook(7) }

        binding.shareApp.setOnClickListener { shareApp() }
    }

    fun openBook(request: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToReadFragment(request)
        findNavController().navigate(action)
    }

    private fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            val shareMessage = """
                            https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            Toast.makeText(context, "خلل في مشاركة التطبيق، المرجو الإعادة", Toast.LENGTH_SHORT)
                .show()
        }
    }


}
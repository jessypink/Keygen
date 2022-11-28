package com.example.keygen

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.keygen.databinding.FragmentSecondBinding
import java.security.AccessController.getContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@Suppress("UNREACHABLE_CODE")
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null


    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
        var context = getActivity()?.getApplicationContext()
        val language = arrayOf<String>("C","C++","Java",".Net","Kotlin","Ruby","Rails","Python","Java Script","Php","Ajax","Perl","Hadoop")
        val arrayAdapter2 =
            context?.let { ArrayAdapter<String>(it, R.layout.simple_list_item_1,language) }
        _binding!!.listView.adapter = arrayAdapter2
        //
        //listView.adapter = arrayAdapter

    }



        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
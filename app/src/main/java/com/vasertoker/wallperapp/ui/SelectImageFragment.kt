package com.vasertoker.wallperapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vasertoker.wallperapp.R
import com.vasertoker.wallperapp.databinding.FragmentSelectImageBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SelectImageFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentSelectImageBinding?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectImageBinding.inflate(inflater, container, false)

        val string = arguments?.getString("string")

        Glide.with(requireActivity()).load(string).into(binding?.image!!)

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}
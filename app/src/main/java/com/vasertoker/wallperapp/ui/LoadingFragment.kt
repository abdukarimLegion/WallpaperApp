package com.vasertoker.wallperapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vasertoker.wallperapp.R
import com.vasertoker.wallperapp.databinding.FragmentLoadingBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LoadingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentLoadingBinding? = null

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

        binding = FragmentLoadingBinding.inflate(inflater, container, false)


        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                println()
            }

            override fun onFinish() {
                setCurrentFragment(HomeFragment())
            }

        }.start()

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoadingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        parentFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )

            replace(R.id.fragmentContainer, fragment)

            commit()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
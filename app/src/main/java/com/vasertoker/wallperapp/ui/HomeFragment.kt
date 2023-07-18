package com.vasertoker.wallperapp.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.vasertoker.retrofit2.retrofit.Common
import com.vasertoker.wallperapp.R
import com.vasertoker.wallperapp.adapter.ImageAdapter
import com.vasertoker.wallperapp.databinding.FragmentHomeBinding
import com.vasertoker.wallperapp.models.Image
import com.vasertoker.wallperapp.models.Result
import com.vasertoker.wallperapp.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentHomeBinding? = null
    lateinit var retrofitService: RetrofitService

    lateinit var imageAdapter: ImageAdapter
    lateinit var list: ArrayList<com.vasertoker.wallperapp.models.Result>
    private var photoType: String = "ALL"
    private var position : Int = 0
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupTabLayout()
    binding?.tabLayout?.getTabAt(position)?.select()
        list = ArrayList()
        imageAdapter = ImageAdapter(list, requireContext())

        loadRv(photoType)

        binding?.rv?.adapter = imageAdapter

        imageAdapter.setOnItemClick(object : ImageAdapter.OnItemClick {
            override fun onClick(result: Result) {
                val selectImageFragment = SelectImageFragment()
                val args = Bundle()
                args.putString("string", result.urls.full)
                selectImageFragment.arguments = args
                setCurrentFragment(selectImageFragment)
            }
        })

        return binding?.root
    }

    private fun loadRv(photoType: String) {
        list.clear()
        retrofitService = Common.retrofitService()
        retrofitService.getImage(photoType, "KVIpoTMGjaHtOGby3J18LX3ZZz6q6p-Hm9elQz3UisQ", 100)
            .enqueue(object : Callback<Image> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<Image>, response: Response<Image>) {
                    if (response.isSuccessful) {
                        list.addAll(response.body()?.results!!)
                        Log.i(TAG, "onResponse: ${list.size}")
                        imageAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<Image>, t: Throwable) {

                }


            })
    }


    private fun setupTabLayout() {
        binding?.tabLayout?.apply {
            addTab(this.newTab().setText("ALL"))
            addTab(this.newTab().setText("NEW"))
            addTab(this.newTab().setText("ANIMAL"))
            addTab(this.newTab().setText("TECHNOLOGY"))
            addTab(this.newTab().setText("NATURE"))
            addTab(this.newTab().setText("GIRL"))
            addTab(this.newTab().setText("BOY"))

            // tabGravity = TabLayout.GRAVITY_FILL

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let {
                        when (it) {
                            0 -> {
                                photoType = "ALL"

                            }
                            1 -> {

                                photoType = "NEW"
                            }
                            2 -> {
                                photoType = "ANIMAL"
                            }
                            3 -> {
                                photoType = "TECHNOLOGY"
                            }
                            4 -> {
                                photoType = "NATURE"
                            }

                            5 -> {
                                photoType = "GIRL"
                            }

                            6 -> {
                                photoType = "BOY"
                            }

                        }
                        loadRv(photoType)
                        position = it

                        Log.i(TAG, "onTabSelected: $it")
                        Log.i(TAG, "onTabSelected: $tab")
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
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
            addToBackStack("fragment")
            commit()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
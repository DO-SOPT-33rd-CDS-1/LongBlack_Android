package com.example.longdroid.presentation.library

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.longdroid.R
import com.example.longdroid.di.ServicePool
import com.example.longdroid.data.model.response.LibraryResponse
import com.example.longdroid.databinding.ActivityLibraryBinding
import com.example.longdroid.util.binding.BindingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryActivity : BindingActivity<ActivityLibraryBinding>(R.layout.activity_library) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ServicePool.libraryService.getStampCount().enqueue(
            object :
                Callback<LibraryResponse> {
                override fun onResponse(
                    call: Call<LibraryResponse>,
                    response: Response<LibraryResponse>,
                ) {
                    if (response.isSuccessful) {
                        val stampCount = response.body()?.stampCount ?: 0

                        updateStampImages(stampCount)
                    }
                }

                override fun onFailure(call: Call<LibraryResponse>, t: Throwable) {
                    Log.e("API_REQUEST_FAILURE", "API 요청 실패: ${t.message}")
                }
            },
        )
    }

    private fun updateStampImages(stampCount: Int) {
        val stampImageViews = listOf(
            binding.ivLibraryStamp1,
            binding.ivLibraryStamp2,
            binding.ivLibraryStamp3,
            binding.ivLibraryStamp4,
            binding.ivLibraryStamp5,
            binding.ivLibraryStamp6,
            binding.ivLibraryStamp7,
            binding.ivLibraryStamp8,
            binding.ivLibraryStamp9,
            binding.ivLibraryStamp10,
            binding.ivLibraryStamp11,
            binding.ivLibraryStamp12,
        )

        for (i in 0 until stampCount) {
            stampImageViews.getOrNull(i)?.let { imageView ->

                Glide.with(this)
                    .load(R.drawable.ic_coffee_sticker_on_small)
                    .into(imageView)
            }
        }
    }
}

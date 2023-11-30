package com.example.longdroid.presentation.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityHomeBinding
import com.example.longdroid.presentation.library.LibraryActivity
import com.example.longdroid.presentation.notelist.NoteListActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val imageResources = arrayOf(
        R.drawable.img_home_event_green,
        R.drawable.img_home_event_red,
        R.drawable.img_home_event_blue,
    )
    private var currentImageIndex = 0

    private val updateInterval: Long = 1000 // 1초마다 업데이트
    private val handler = Handler(Looper.getMainLooper())

    private val updateTimeTask = object : Runnable {
        override fun run() {
            updateRemainingTime()
            handler.postDelayed(this, updateInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(getLayoutInflater())
        val view: View = binding.root
        setContentView(view)

        initViews()
        initButtons()
        updateRemainingTime()
    }

    private fun initViews() {
        binding.ivEventList.load(imageResources[currentImageIndex])

        binding.btnGoToLibrary.setOnClickListener {
            startActivity(Intent(this, LibraryActivity::class.java))
        }

        binding.btnGoToNote.setOnClickListener {
            startActivity(Intent(this, NoteListActivity::class.java))
        }
    }

    private fun initButtons() {
        binding.btnNext.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % imageResources.size
            binding.ivEventList.load(imageResources[currentImageIndex])
        }

        binding.btnBefore.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                imageResources.size - 1
            }
            binding.ivEventList.load(imageResources[currentImageIndex])
        }
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(updateTimeTask, updateInterval)
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(updateTimeTask)
    }

    private fun updateRemainingTime() {
        TimeManager.updateRemainingTime()
        val (remainingHour, remainingMinute, remainingSecond) = TimeManager.remainingTime

        binding.tvHour.text = String.format("%02d", remainingHour)
        binding.tvMinute.text = String.format("%02d", remainingMinute)
        binding.tvSecond.text = String.format("%02d", remainingSecond)
    }
}

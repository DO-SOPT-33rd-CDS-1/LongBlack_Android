package com.example.longdroid.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityHomeBinding
import com.example.longdroid.presentation.library.LibraryActivity
import com.example.longdroid.presentation.notelist.NoteListActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        val imageView: ImageView = binding.ivEventList
        val nextButton: ImageButton = binding.btnNext
        val beforeButton: ImageButton = binding.btnBefore

        nextButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % imageResources.size
            imageView.setImageResource(imageResources[currentImageIndex])
        }

        beforeButton.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                imageResources.size - 1
            }
            imageView.setImageResource(imageResources[currentImageIndex])
        }
        binding.btnGoToLibrary.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }
        binding.btnGoToNote.setOnClickListener {
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }

        updateRemainingTime()
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
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTimeString = dateFormat.format(currentTime)

        val currentTimeParts = currentTimeString.split(":")
        val currentHour = currentTimeParts[0].toInt()
        val currentMinute = currentTimeParts[1].toInt()
        val currentSecond = currentTimeParts[2].toInt()

        val remainingHour = 24 - currentHour - 1
        val remainingMinute = 59 - currentMinute
        val remainingSecond = 59 - currentSecond

        binding.tvHour.text = String.format("%02d", remainingHour)
        binding.tvMinute.text = String.format("%02d", remainingMinute)
        binding.tvSecond.text = String.format("%02d", remainingSecond)
    }
}

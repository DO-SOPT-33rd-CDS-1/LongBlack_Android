package com.example.longdroid.presentation


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.longdroid.R
import com.example.longdroid.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val imageResources = arrayOf(R.drawable.img_home_event_green, R.drawable.img_home_event_red, R.drawable.img_home_event_blue)
    private var currentImageIndex = 0

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




    }
}

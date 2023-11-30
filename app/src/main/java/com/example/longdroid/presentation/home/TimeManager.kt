package com.example.longdroid.presentation.home

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object TimeManager {
    var remainingTime: Triple<Int, Int, Int> = Triple(0, 0, 0)

    fun updateRemainingTime() {
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

        remainingTime = Triple(remainingHour, remainingMinute, remainingSecond)
    }
}

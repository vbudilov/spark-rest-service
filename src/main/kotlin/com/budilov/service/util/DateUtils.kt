package com.budilov.service.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }

    @JvmStatic
    private fun yesterday(): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        return cal.time
    }

    @JvmStatic
    fun getToday(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        return dateFormat.format(cal.time)
    }

    @JvmStatic
    fun getYesterdayDateString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val dateFormat = SimpleDateFormat("MM/dd/yyyy")

        return dateFormat.format(yesterday())
    }

    @JvmStatic
    fun getDateString(daysAgo: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -daysAgo)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(cal.time)
    }


}

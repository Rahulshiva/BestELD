package com.eld.besteld.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class TimeUtility {
    companion object {
        fun debug(debugMessage : String) {
            println("[DEBUG] $debugMessage")
        }

        fun currentDateUTC(): LocalDateTime {
            var localeTime = LocalDateTime.now(ZoneOffset.UTC);
            return  localeTime
        }

        fun timeForDateString(dateString: String): String {
//            String str = "2016-03-04 11:30";
            val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") //2021-03-11T19:01:39.428
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            val dateTime = LocalDateTime.parse(dateString)
            val timeString = dateTime.hour.toString() + ":" + dateTime.minute.toString()
            return timeString
        }

        fun timeToStartOfTheDay(inDate: LocalDateTime) {
            //var dateTeimObj = LocalDateTime.now()
            var utcTime = inDate.atZone(ZoneOffset.UTC);
            print(utcTime)
        }

        fun startOfTheDay(inDate: Date): Int {
            val instant: Instant = inDate.toInstant()

            //Converting the Date to LocalDate

            //Converting the Date to LocalDate
            val localDate: LocalDate = instant.atZone(ZoneOffset.UTC).toLocalDate()
            println("Local Date is: $localDate")


            val test1 = localDate.atStartOfDay(ZoneOffset.UTC)
            val test2 = localDate.atStartOfDay()

            return  6000909
        }

    }
}
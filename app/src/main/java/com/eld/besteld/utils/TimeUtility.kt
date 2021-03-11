package com.eld.besteld.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class TimeUtility {
    companion object {
        fun debug(debugMessage : String) {
            println("[DEBUG] $debugMessage")
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
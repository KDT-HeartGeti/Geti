package com.example.myapplication.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

data class CalendarUiModel(
    val selectedDate: Date, // the date selected by the User. by default is Today.
    val visibleDates: List<Date> // the dates shown on the screen
) {

    val startDate: Date = visibleDates.first() // the first of the visible dates
    val endDate: Date = visibleDates.last() // the last of the visible dates

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E")) // get the day by formatting the date
    }
}
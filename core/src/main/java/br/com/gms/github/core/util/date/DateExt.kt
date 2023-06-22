package br.com.gms.github.core.util.date

import java.text.SimpleDateFormat
import java.util.*

const val FULL_BR_DATE_MASK = "dd MMMM yyyy"
const val FULL_US_DATE_TIME_MASK = "yyyy-MM-dd'T'HH:mm:ss"

fun String.convertFromUSFormatToDateBR(): String {
    return try {
        this.convertFromUSFormatToDate()?.let {
            SimpleDateFormat(FULL_BR_DATE_MASK, Locale.getDefault()).run {
                isLenient = false
                format(it)
            }
        } ?: this
    } catch (e: Exception) {
        this
    }
}

fun String.convertFromUSFormatToDate(): Date? {
    return try {
        SimpleDateFormat(FULL_US_DATE_TIME_MASK, Locale.getDefault()).run {
            isLenient = false
            parse(this@convertFromUSFormatToDate)
        }
    } catch (e: Exception) {
        null
    }
}
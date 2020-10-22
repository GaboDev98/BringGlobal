package com.gabodev.bringglobal.extensions

import java.text.Normalizer
import com.gabodev.bringglobal.BuildConfig

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun String.unAccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun makeIconURL(icon:String): String {
    val section="img/w/"
    return "${BuildConfig.BASE_URL}$section$icon.png"
}
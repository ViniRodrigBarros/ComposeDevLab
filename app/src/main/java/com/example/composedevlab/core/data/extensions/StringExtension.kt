package com.example.composedevlab.core.data.extensions

fun String.toNoDiacritic(): String {
    val diacritics = "ÀÁÂÃÄÅàáâãäåÒÓÔÕÕÖØòóôõöøÈÉÊËèéêëðÇçÐÌÍÎÏìíîïÙÚÛÜùúûüÑñŠšŸÿýŽž"
    val noDiacritics = "AAAAAAaaaaaaOOOOOOOooooooEEEEeeeeeCcDIIIIiiiiUUUUuuuuNnSsYyyZz"
    return buildString {
        this@toNoDiacritic.forEach { char ->
            val index = diacritics.indexOf(char)
            append(if (index >= 0) noDiacritics[index] else char)
        }
    }
}

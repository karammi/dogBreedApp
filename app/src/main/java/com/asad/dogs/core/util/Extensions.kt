package com.asad.dogs.core.util

fun String.firstCap() = this.replaceFirstChar { it.uppercase() }

fun String.createListFromString(separator: String): List<String> {
    if (this.isNullOrBlank()) {
        return emptyList()
    }
    return if (separator in this) {
        this.split(separator)
    } else {
        listOf(this)
    }
}

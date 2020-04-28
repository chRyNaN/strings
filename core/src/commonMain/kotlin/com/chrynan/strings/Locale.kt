package com.chrynan.strings

class Locale(
    val language: String,
    val script: String,
    val country: String,
    val variant: String
) {

    companion object {

        // TODO
        val default: Locale = Locale("", "", "", "")
    }
}
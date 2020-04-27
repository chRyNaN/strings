package com.chrynan.resources

class Locale(
    val language: String,
    val script: String,
    val country: String,
    val variant: String
) {

    companion object {

        val default: Locale = TODO()
    }
}
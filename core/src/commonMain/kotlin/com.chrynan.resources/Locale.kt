package com.chrynan.resources

class Locale(
    val language: kotlin.String,
    val script: kotlin.String,
    val country: kotlin.String,
    val variant: kotlin.String
) {

    companion object {

        val default: Locale = TODO()
    }
}
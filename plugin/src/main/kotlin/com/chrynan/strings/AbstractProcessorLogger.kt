package com.chrynan.strings

import com.chrynan.logger.LogInitializer
import com.chrynan.logger.LogType
import com.chrynan.logger.Loggable
import de.jensklingenberg.mpapt.model.AbstractProcessor
import kotlin.String

class AbstractProcessorLogger(private val processor: AbstractProcessor) : Loggable,
    LogInitializer {

    override fun init() = logInfo(message = "Initializing ${javaClass.simpleName}")

    override fun log(logType: LogType, tag: String, message: String?, throwable: Throwable?) =
        processor.log(logType, "Tag: $tag; Message: $message; Throwable: $throwable")

    private fun AbstractProcessor.log(type: LogType, message: kotlin.String) {
        log("$type: $message")
    }
}
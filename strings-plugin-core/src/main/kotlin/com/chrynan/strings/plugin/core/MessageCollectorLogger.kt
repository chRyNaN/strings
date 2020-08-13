package com.chrynan.strings.plugin.core

import com.chrynan.logger.LogInitializer
import com.chrynan.logger.LogType
import com.chrynan.logger.Loggable
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import kotlin.String

class MessageCollectorLogger(private val messageCollector: MessageCollector) : Loggable,
    LogInitializer {

    override fun init() = logInfo(message = "Initializing ${javaClass.simpleName}")

    override fun log(logType: LogType, tag: String, message: String?, throwable: Throwable?) {
        val severity = when (logType) {
            LogType.DEBUG -> CompilerMessageSeverity.LOGGING
            LogType.INFO -> CompilerMessageSeverity.INFO
            LogType.VERBOSE -> CompilerMessageSeverity.OUTPUT
            LogType.WARNING -> CompilerMessageSeverity.WARNING
            LogType.ERROR -> CompilerMessageSeverity.EXCEPTION
            LogType.WTF -> CompilerMessageSeverity.EXCEPTION
        }

        messageCollector.log(severity, "Tag: $tag; Message: $message; Throwable: $throwable")
    }

    private fun MessageCollector.log(severity: CompilerMessageSeverity, message: String) = report(severity, message)
}
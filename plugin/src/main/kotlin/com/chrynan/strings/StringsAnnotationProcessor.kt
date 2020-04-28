package com.chrynan.strings

import de.jensklingenberg.mpapt.model.AbstractProcessor
import de.jensklingenberg.mpapt.model.RoundEnvironment

class StringsAnnotationProcessor: AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(
    )

    override fun process(roundEnvironment: RoundEnvironment) {
    }
}
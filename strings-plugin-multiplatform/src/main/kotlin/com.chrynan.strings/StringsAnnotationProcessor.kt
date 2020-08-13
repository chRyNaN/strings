package com.chrynan.strings

import com.chrynan.kotlinwriter.codeBlockString
import com.chrynan.kotlinwriter.kotlinFile
import com.chrynan.strings.core.Locale
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.asClassName

class StringsAnnotationProcessor : BaseStringsAnnotationProcessor() {

    override fun handleAnnotatedFiles(annotatedFileElements: Set<AnnotatedFileElement>) {

    }

    private fun createStringFile(file: AnnotatedFileElement) =
        kotlinFile(packageName = file.nameAndLocation.packageName, fileName = file.nameAndLocation.fileName) {
            nestedObject(name = file.nameAndLocation.fileName) {

                // Static Strings
                file.staticStrings.forEach {
                    function(name = it.name) {
                        parameter(name = Names.PARAM_LOCALE, type = String::class) {
                            defaultValue = CodeBlock.of(Locale.default)
                        }
                        returns = String::class.asClassName()
                        body = codeBlockString {
                            "return ${Names.STRINGS_CLASS}.${Names.FUN_GET_STRING}(${Names.PARAM_ID}=${it.name},${Names.PARAM_LOCALE}=${it.locale})"
                        }
                    }
                }

                // Dynamic Strings
                file.dynamicStrings.forEach {
                    function(name = it.name) {
                        parameter(name = Names.PARAM_LOCALE, type = String::class) {
                            defaultValue = CodeBlock.of(Locale.default)
                        }

                        // TODO parse value and add parameters

                        returns = String::class.asClassName()
                        body = codeBlockString {
                            "return ${Names.STRINGS_CLASS}.${Names.FUN_GET_DYNAMIC_STRING}(${Names.PARAM_ID}=${it.name},${Names.PARAM_LOCALE}=${it.locale})"
                        }
                    }
                }
            }
        }
}
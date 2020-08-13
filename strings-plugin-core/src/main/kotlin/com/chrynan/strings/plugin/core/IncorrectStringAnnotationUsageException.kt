package com.chrynan.strings.plugin.core

class IncorrectStringAnnotationUsageException(names: List<String>) :
    RuntimeException("The following annotations should only be used within their parent annotations: names = $names")
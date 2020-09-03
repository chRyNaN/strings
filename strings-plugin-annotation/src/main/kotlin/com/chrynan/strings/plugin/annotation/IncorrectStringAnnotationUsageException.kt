package com.chrynan.strings.plugin.annotation

class IncorrectStringAnnotationUsageException(names: List<String>) :
    RuntimeException("The following annotations should only be used within their parent annotations: names = $names")
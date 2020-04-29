package com.chrynan.strings

class IncorrectStringAnnotationUsageException(names: List<String>) :
    RuntimeException("The following annotations should only be used within their parent annotations: names = $names")
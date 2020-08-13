package com.chrynan.strings.creator.input.json

class InvalidStringValueTypeException(
    expectedType: String,
    actualType: String?
) : RuntimeException("Invalid String Value Type: expected = $expectedType; actual = $actualType")
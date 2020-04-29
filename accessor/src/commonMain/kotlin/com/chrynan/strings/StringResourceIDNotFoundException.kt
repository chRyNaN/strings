package com.chrynan.strings

class StringResourceIDNotFoundException(resourceID: ResourceID, locale: String, quantity: Quantity? = null) :
    RuntimeException("A resource was not found for the provided ResourceID and Locale. resourceID = $resourceID; locale = $locale; quantity = $quantity")
package com.chrynan.strings.accessor

import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID

class StringResourceIDNotFoundException(resourceID: ResourceID, locale: String, quantity: Quantity? = null) :
    RuntimeException("A resource was not found for the provided ResourceID and Locale. resourceID = $resourceID; locale = $locale; quantity = $quantity")
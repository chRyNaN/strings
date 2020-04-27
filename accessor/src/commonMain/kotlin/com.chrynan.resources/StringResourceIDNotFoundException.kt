package com.chrynan.resources

class StringResourceIDNotFoundException(resourceID: ResourceID, locale: Locale) :
    RuntimeException("A resource was not found for the provided ResourceID and Locale. resourceID = $resourceID; locale = $locale")
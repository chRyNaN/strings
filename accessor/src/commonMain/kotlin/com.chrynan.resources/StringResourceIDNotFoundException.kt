package com.chrynan.resources

class StringResourceIDNotFoundException(resourceID: StringResourceID) :
    RuntimeException("The provided ${StringResourceID::class.simpleName} was not found. resourceID = $resourceID")
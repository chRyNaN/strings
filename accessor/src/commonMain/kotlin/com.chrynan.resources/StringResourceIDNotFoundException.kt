package com.chrynan.resources

class StringResourceIDNotFoundException(resourceID: ResourceID) :
    RuntimeException("The provided ${ResourceID::class.simpleName} was not found. resourceID = $resourceID")
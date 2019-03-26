# resources
Kotlin Multiplatform Static Resource Accessor and Creator Library

**Note:** This project is in early development stages.
**Warning:** All examples are psuedo-code. The API may change as this project is in active and early development.

The goal of this library is to abstract both the creation and retrieval of resources on Kotlin Multiplatform Projects. Some benefits this library provides include:

* Unifying and sharing common resources between projects
* Accessing the shared resources in a Kotlin Common Module for reusable code

## Accessing Resources

This part of the library can be used independently of the creation part.

* Setup the platform specific `ResourceAccessor` for each platform. For example, in the Android Application class:

```kotlin
fun onCreate() {
    super.onCreate()
    
    Resources.accessor = AndroidResourcesAccessor(appContext = this)
}
```

* Access the values by the `ResourceAccessor`, convenience functions, or related objects.

```kotlin
class MyPresenter : Presenter {

    private val myString by string(StringIds.MY_STRING)
}

// Or

class MyPresenter(accessor: ResourceAccessor) : Presenter,
    ResourceAccessor by accessor {
    
    private val myString by string(StringIds.MY_STRING)
}

// Or

class MyPresenter : Presenter {

    fun test() {
        val myString = Strings.getString(StringIds.MY_STRING)
        val anotherApproach = resourceAccessor.stringResourceAccessor.getString(StringIds.MY_STRING)
    }
}
```

The API is versatile and can be used how the consumer desires. 

## Creating Resources

This part of the library requires using a Gradle Plugin to generate the resource files for each platform. 

* Create a resource file using the provided DSL in the project's `buildSrc` directory. 

It has to be in the `buildSrc` directory because the classes need to be compiled before the Gradle Plugin runs, so it can retrieve the data needed to generate the resource files.

```kotlin
val myStrings = strings(fileName = "strings") {
    string("my_string") { "Some Text" }
    string(name = "another_string", value = "Some Other Text")
    array("my_array") {
        item("item_one") { "Item One Value" }
        item(name = "item_two", value = "Item Two Value")
    }
}
```

* Apply and configure the plugin in the project's `build.gradle` file.

```groovy
createResources {
    strings = [myStrings]
}
```

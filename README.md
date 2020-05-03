# strings
Kotlin Multi-platform String Resource Accessor and Creator Library

**Note:** This project is in early development stages.

The goal of this library is to abstract both the creation and retrieval of strings in Kotlin Multi-platform Projects.

## Using the library

**Create the Strings:**

```kotlin
// File MyStrings - File name and location can be anything

@file:StaticString(name = "myStaticString", value = "Hello World")

@file:HtmlString(name = "myHtmlString", value = "Some text with <b>Html</b> elements.", locale = "en")

@file:DynamicString(name = "myDynamicString", value = "Hello %1\$s", locale = "en")

package com.chrynan.strings
```

**Add the generated `StringAccessor`:**

```kotlin
Strings.accessor = GeneratedStringAccessor()
```

**Access the String Resources:**

```kotlin
// Using the generated String Resource IDs

val myStaticString by string(resourceID = StringResIDs.myStaticString)

val myHtmlString by htmlString(resourceID = StringResIDs.myHtmlString, locale = "en")

val dynamicStringFormatter = dynamicStringFormatter(resourceID = StringResIDs.myDynamicString, locale = "en")
val myDynamicString = dynamicStringFormatter("$name")

// Or using the generated String Objects

MyStrings.myStaticString()

MyStrings.myHtmlString(locale = "en")

MyStrings.myDynamicString(locale = "en", "$name")
```

## Definitions

### StaticString
Static Strings are String values that are not formatted, meaning that they do not require any arguments to obtain a String value.

```kotlin
@file:StaticString(name = "hello_world", value = "Hello World", locale = "en")
@file:StaticString(name = "hello_world", value = "สวัสดีชาวโลก", locale = "th")
@file:StaticString(name = "hello_world", value = "Hallo Welt", locale = "de")
@file:StaticString(name = "hello_world", value = "Hola Mundo", locale = "es")
```

### StringArray
String Arrays are Arrays of Static String values. They are defined per locale.

```kotlin
@file:StringArray(
    name = "numbers",
    locale = "en",
    values = [
        StringArrayItem(value = "one"),
        StringArrayItem(value = "two"),
        StringArrayItem(value = "three"),
        StringArrayItem(value = "four"),
        StringArrayItem(value = "five")
    ]
)
```

### DynamicString
Dynamic Strings are String values that can be formatted by providing additional arguments at runtime. 

```kotlin
@file:DynamicString(name = "hello_name", value = "Hello %1\$s", locale = "en")
@file:DynamicString(name = "hello_name", value = "Hallo %1\$s", locale = "de")
```

**Dynamic String Arguments:**

Dynamic String arguments take the following format: `%[name]\$[type]`. Dynamic Strings with no arguments are essentially Static Strings.

**Supported Argument Types:**

* Any -> `\$a`
* Char -> `\$c`
* String -> `\$s`
* Int -> `\$i`
* Long -> `\$l`
* Float -> `\$f`
* Double -> `\$d`

Any other argument type value will be ignored and will not be considered as an argument in the String.

**Argument Names:**

All Dynamic String arguments need to be prefaced with an argument name (`%1`). These names will be used to create input argument names in the generated code.

Dynamic Strings can use the same argument multiple times:
```kotlin
@file:DynamicString(name = "greeting", value = "Hello %name\$s! How are you %name\$s?")
```

If Dynamic String names are a number, they are prefaced with text in the generated code to make it a valid Kotlin parameter name.

**Escaping Arguments:**

Dynamic String arguments always take the following format: `%[name]\$[type]`. However, if you have a String that contains that format but don't want it registered as an argument, simply escape it with two consecutive backslash characters ('\'):
```kotlin
@file:DynamicString(name = "complex_text", value = "Escaped text that looks like this: \\%a\$s.")
```

The output String value will not contain the two preceding escape characters and will not register the text as an argument.

### HtmlString
Html Strings are Dynamic Strings that contain Html markup. No special parsing of the markup is performed in this library. Instead, this is a way to organize Html Strings separately so that they can be handled appropriately.

```kotlin
@file:HtmlString(name = "greeting", value = "Hello <b>%1\$s</b>")
```
### StringPlurals
String Plurals are Dynamic Strings that are grouped together and distinguished by a provided `Quantity`. Each of the `StringPluralItems` must have the same amount of arguments.

```kotlin
@file:StringPlurals(
    name = "items",
    locale = "en",
    values = [
        StringPluralItem(quantity = Quantity.ONE, value = "%1\$i item"),
        StringPluralItem(quantity = Quantity.MANY, value = "%1\$i items")
    ]
)
```

**Note:** That it is up to the accessor of the `StringPlurals` value to provide a `Quantity` to retrieve the appropriate String.

### StringGroup
A String Group is a collection of related single String resources that have different values for each locale. This is a convenient way of organizing String resources.

```kotlin
@file:StringGroup(
    name = "hello_world",
    type = StringGroupType.STATIC,
    values = [
        StringGroupItem(value = "Hello World", locale = "en"),
        StringGroupItem(value = "สวัสดีชาวโลก", locale = "th"),
        StringGroupItem(value = "Hallo Welt", locale = "de"),
        StringGroupItem(value = "Hola Mundo", locale = "es")
    ]
)
```

The above example is equivalent to the following:

```kotlin
@file:StaticString(name = "hello_world", value = "Hello World", locale = "en")
@file:StaticString(name = "hello_world", value = "สวัสดีชาวโลก", locale = "th")
@file:StaticString(name = "hello_world", value = "Hallo Welt", locale = "de")
@file:StaticString(name = "hello_world", value = "Hola Mundo", locale = "es")
```

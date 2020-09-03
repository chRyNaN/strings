# strings
Kotlin Multi-platform String Resource Accessor and Creator Library

**Note:** This project is in early development stages and is not ready for production use.

The goal of this library is to abstract both the creation and retrieval of strings in Kotlin Multi-platform Projects.

## Using the library

### Add the Dependencies

#### Plugin
```groovy
apply plugin: "com.chrynan.strings.plugin.gradle.json"
```

#### Runtime Dependency
```groovy
repositories {
    maven {
        url = "https://dl.bintray.com/chrynan/chrynan"
    }
}

dependencies {
    implementation "com.chrynan.strings:strings-accessor:$VERSION"
}
```

### Configure the Plugin

```groovy
jsonStrings {
    inputPaths = ["location/to/json/string/file.json"]
    outputPath = "src/commonMain/kotlin/com.chrynan.example"
    outputPackageName = "com.chrynan.example"
}
```

### Run the Gradle Task

```
./gradlew generateStringsFromJsonInput
```

### Create the Strings

#### JSON
Create a JSON file with a top-level array containing the String objects.
```json
[{
  "type": "static",
  "name": "helloWorld",
  "value": "Hello World"
}]
```

#### Kotlin Annotations (Not yet supported)
```kotlin
// File MyStrings - File name and location can be anything
@file:StaticString(name = "helloWorld", value = "Hello World")
```

### Add the generated `StringAccessor`

```kotlin
Strings.accessor = KotlinStrings() // Generated Class
```

### Access the String Resources

```kotlin
// Using the generated String Resource IDs
val myStaticString by string(resourceID = StringResIDs.myStaticString)
```

## Definitions

### StaticString
Static Strings are String values that are not formatted, meaning they do not require any arguments to obtain a String value.

#### JSON
```json
{
  "type": "static",
  "name": "hello_world",
  "value": "Hello World"
}
```

#### Kotlin Annotations (Not yet supported)
```kotlin
@file:StaticString(name = "hello_world", value = "Hello World", locale = "en")
```

#### Kotlin Accessibility
```kotlin
val helloWorld = string(resourceID = StringResID.hello_world)
```

### StringArray
String Arrays are Arrays of Static String values.

#### JSON
```json
{
  "type": "array",
  "name": "numbers",
  "values": ["one", "two", "three", "four", "five"]
}
```

#### Kotlin Annotations (Not yet supported)
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

#### Kotlin Accessibility
```kotlin
val numbers = stringArray(resourceID = StringResID.numbers)
```

### DynamicString
Dynamic Strings are String values that can be formatted by providing additional arguments at runtime. 

#### JSON
```json
{
  "type": "dynamic",
  "name": "hello_name",
  "value": "Hello ${name:String}"
}
```

#### Kotlin Annotations (Not yet supported)
```kotlin
@file:DynamicString(name = "hello_name", value = "Hello \${name:String}", locale = "en")
```

**Dynamic String Arguments:**

Dynamic String arguments take the following format: `${name:type}`. Or if in Kotlin code the `$` character must be escaped: `\${name:type}`. Dynamic Strings with no arguments are essentially Static Strings.

**Supported Argument Types:**

| Kotlin Type | Expanded Definition | Brief Definition |
| --- | --- | --- |
| Any | `${name:Any}` | `${name:a}` |
| Char | `${name:Char}` | `${name:c}` |
| String | `${name:String}` | `${name:s}` |
| Int | `${name:Int}` | `${name:i}` |
| Long | `${name:Long}` | `${name:l}` |
| Float | `${name:Float}` | `${name:f}` |
| Double | `${name:Double}` | `${name:d}` |
| Custom | `${name:com.chrynan.Example}` | --- |

**Custom Types:**
Custom types can be provided with the following syntax: `${name:packageName.typeName}`.

The provided custom type name must be the fully qualified Kotlin type name.

**Argument Names:**

All Dynamic String arguments need to be prefaced with an argument name. These names will be used to create input argument names in the generated code.

* Dynamic Strings can use the same argument multiple times:
```
"Hello ${name:String}! How are you ${name}?"
```
* If Dynamic String reuse same argument multiple times, the type only has to be provided once, for the first usage of the name.
* Non-matching types for the same argument name will cause an error.
* Argument names cannot begin with a number
* Argument names must conform to Kotlin property name requirements.

**Escaping Arguments:**

To escape the `$` character so that it doesn't register as an argument, use the `\` character: `\$`.

### HtmlString
Html Strings are Dynamic Strings that contain Html markup. No special parsing of the markup is performed in this library. Instead, this is a way to organize Html Strings separately so that they can be handled appropriately.

#### JSON
```json
{
  "type": "html",
  "name": "hello_name",
  "value": "Hello <b>${name:String}</b>"
}
```

#### Kotlin Annotations (Not yet supported)
```kotlin
@file:HtmlString(name = "greeting", value = "Hello <b>\${name:String}</b>")
```

### StringPlurals
String Plurals are Dynamic Strings that are grouped together and distinguished by a provided `Quantity`. Each of the `StringPluralItems` must have the same amount of arguments.

#### JSON
```json
{
  "type": "plurals",
  "name": "items",
  "values": [
    {
      "quantity": "one",
      "value": "${number:Int} item"
    },
    {
      "quantity": "many",
      "value": "${number:Int} items"
    } 
  ]
}
```

#### Kotlin Annotations (Not yet supported)
```kotlin
@file:StringPlurals(
    name = "items",
    locale = "en",
    values = [
        StringPluralItem(quantity = Quantity.ONE, value = "\${number:Int} item"),
        StringPluralItem(quantity = Quantity.MANY, value = "\${number:Int} items")
    ]
)
```

**Note:** That it is up to the accessor of the `StringPlurals` value to provide a `Quantity` to retrieve the appropriate String.

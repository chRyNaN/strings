package com.chrynan.strings

data class StringResourcesFile(
    val fileName: String,
    val singleStringResources: Set<SingleStringResource>
)

data class SingleStringResource(
    val identifier: StringResourceIdentifier,
    val value: String
)

data class PluralStringResource(
    val identifier: StringResourceIdentifier,
    val quantities: Set<QuantityStringResource>
)

data class StringArrayResource(
    val identifier: StringResourceIdentifier,
    val values: List<String>
)

data class QuantityStringResource(
    val value: String,
    val quantity: Quantity
)

class StringResourcesFileBuilder(private val fileName: String) {

    private val singleStringResources = mutableSetOf<SingleStringResource>()
    private val pluralStringResources = mutableSetOf<PluralStringResource>()
    private val stringArrayResources = mutableSetOf<StringArrayResource>()

    fun string(identifier: StringResourceIdentifier, value: String) {
        singleStringResources += SingleStringResource(
            identifier = identifier,
            value = value
        )
    }

    fun string(name: String, value: String) {
        singleStringResources += SingleStringResource(
            identifier = NameStringResourceIdentifier(name = name),
            value = value
        )
    }

    fun string(identifier: StringResourceIdentifier, accessor: () -> String) {
        singleStringResources += SingleStringResource(
            identifier = identifier,
            value = accessor.invoke()
        )
    }

    fun string(name: String, accessor: () -> String) {
        singleStringResources += SingleStringResource(
            identifier = NameStringResourceIdentifier(name = name),
            value = accessor.invoke()
        )
    }

    fun plurals(name: String, builder: PluralStringBuilder.() -> Unit) {
        val pluralBuilder = PluralStringBuilder(identifier = NameStringResourceIdentifier(name = name))
        builder.invoke(pluralBuilder)
        pluralStringResources += pluralBuilder.build()
    }

    fun plurals(identifier: StringResourceIdentifier, builder: PluralStringBuilder.() -> Unit) {
        val pluralBuilder = PluralStringBuilder(identifier = identifier)
        builder.invoke(pluralBuilder)
        pluralStringResources += pluralBuilder.build()
    }

    fun array(name: String, builder: StringArrayBuilder.() -> Unit) {
        val arrayBuilder = StringArrayBuilder(identifier = NameStringResourceIdentifier(name = name))
        builder.invoke(arrayBuilder)
        stringArrayResources += arrayBuilder.build()
    }

    fun array(identifier: StringResourceIdentifier, builder: StringArrayBuilder.() -> Unit) {
        val arrayBuilder = StringArrayBuilder(identifier = identifier)
        builder.invoke(arrayBuilder)
        stringArrayResources += arrayBuilder.build()
    }

    internal fun build() = StringResourcesFile(fileName = fileName, singleStringResources = singleStringResources)
}

class PluralStringBuilder(private val identifier: StringResourceIdentifier) {

    private val quantityStringResources = mutableSetOf<QuantityStringResource>()

    fun quantity(quantity: Quantity, value: String) {
        quantityStringResources += QuantityStringResource(value = value, quantity = quantity)
    }

    fun quantity(quantity: Quantity, accessor: () -> String) {
        quantityStringResources += QuantityStringResource(value = accessor.invoke(), quantity = quantity)
    }

    internal fun build() = PluralStringResource(identifier = identifier, quantities = quantityStringResources)
}

class StringArrayBuilder(private val identifier: StringResourceIdentifier) {

    private val arrayValues = mutableListOf<String>()

    fun item(accessor: () -> String) {
        arrayValues += accessor.invoke()
    }

    fun item(value: String) {
        arrayValues += value
    }

    internal fun build() = StringArrayResource(identifier = identifier, values = arrayValues)
}

data class NameStringResourceIdentifier(private val name: String) : StringResourceIdentifier {

    override val id = name
}

enum class Quantity {

    ZERO,
    ONE,
    TWO,
    FEW,
    MANY,
    OTHER
}

fun strings(fileName: String, builder: StringResourcesFileBuilder.() -> Unit): StringResourcesFile {
    val fileBuilder = StringResourcesFileBuilder(fileName = fileName)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}
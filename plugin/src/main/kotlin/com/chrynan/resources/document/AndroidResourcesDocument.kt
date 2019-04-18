package com.chrynan.resources.document

import com.chrynan.pixel.DependencyIndependentPixels
import com.chrynan.pixel.Pixels
import com.chrynan.pixel.PointPixels
import com.chrynan.pixel.ScaledPixels
import com.chrynan.resources.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilder
import javax.xml.transform.dom.DOMSource

class AndroidResourcesDocument private constructor(
    private val document: Document,
    private val resourcesElement: Element
) : ResourcesDocument {

    companion object {

        private const val TAG_NAME_RESOURCES = "resources"
        private const val TAG_NAME_BOOLEAN = "bool"
        private const val TAG_NAME_COLOR = "color"
        private const val TAG_NAME_DIMEN = "dimen"
        private const val TAG_NAME_ID = "item"
        private const val TAG_NAME_INTEGER = "integer"
        private const val TAG_NAME_INTEGER_ARRAY = "integer-array"
        private const val TAG_NAME_INTEGER_ARRAY_ITEM = "item"
        private const val TAG_NAME_STRING = "string"
        private const val TAG_NAME_PLURALS = "plurals"
        private const val TAG_NAME_PLURALS_ITEM = "item"
        private const val TAG_NAME_STRING_ARRAY = "string-array"
        private const val TAG_NAME_STRING_ARRAY_ITEM = "item"
        private const val TAG_NAME_FLOAT = "item"

        private const val ATTRIBUTE_NAME = "name"
        private const val ATTRIBUTE_QUANTITY = "quantity"
        private const val ATTRIBUTE_TYPE = "type"
        private const val ATTRIBUTE_FORMAT = "format"

        fun newInstance(documentBuilder: DocumentBuilder): AndroidResourcesDocument {
            val document = documentBuilder.newDocument()
            // <resources>...</resources>
            val resources = document.createElement(TAG_NAME_RESOURCES)
            document.appendChild(resources)
            return AndroidResourcesDocument(
                document = document,
                resourcesElement = resources
            )
        }
    }

    val source: DOMSource
        get() = DOMSource(document)

    override fun addBoolean(resource: BooleanResource) {
        // <bool name="boolean_name">true</bool>
        val element = document.createElement(TAG_NAME_BOOLEAN)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        element.appendChild(document.createTextNode(resource.value.toString()))
        addElement(element = element)
    }

    override fun addInteger(resource: IntegerResource) {
        // <integer name="integer_name">0</integer>
        val element = document.createElement(TAG_NAME_INTEGER)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        element.appendChild(document.createTextNode(resource.value.toString()))
        addElement(element = element)
    }

    override fun addIntegerArray(resource: IntegerArrayResource) {
        // <integer-array name="integer_array_name"><item>0</item><item>1</item></integer-array>
        val element = document.createElement(TAG_NAME_INTEGER_ARRAY)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        resource.values.forEach {
            val itemElement = document.createElement(TAG_NAME_INTEGER_ARRAY_ITEM)
            itemElement.appendChild(document.createTextNode(it.toString()))
            element.appendChild(itemElement)
        }
        addElement(element = element)
    }

    override fun addString(resource: SingleStringResource) {
        // <string name="string_name">Some Text Value</string>
        val element = document.createElement(TAG_NAME_STRING)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        element.appendChild(document.createTextNode(resource.value))
        addElement(element = element)
    }

    override fun addStringArray(resource: StringArrayResource) {
        // <string-array name="string_array_name"><item>Some Text</item><item>Some Other Text</item></string-array>
        val element = document.createElement(TAG_NAME_STRING_ARRAY)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        resource.values.forEach {
            val itemElement = document.createElement(TAG_NAME_STRING_ARRAY_ITEM)
            itemElement.appendChild(document.createTextNode(it))
            element.appendChild(itemElement)
        }
        addElement(element = element)
    }

    override fun addPluralString(resource: PluralStringResource) {
        // <plurals name="plurals_name"><item quantity="one">One Text</item><item quantity="two">Two Text</item></plurals>
        val element = document.createElement(TAG_NAME_PLURALS)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        resource.quantities.forEach {
            val quantityElement = document.createElement(TAG_NAME_PLURALS_ITEM)
            quantityElement.setAttribute(ATTRIBUTE_QUANTITY, it.quantity.toString())
            quantityElement.appendChild(document.createTextNode(it.value))
            element.appendChild(quantityElement)
        }
        addElement(element = element)
    }

    override fun addColor(resource: ColorResource) {
        // <color name="color_name">true</bool>
        val element = document.createElement(TAG_NAME_COLOR)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        element.appendChild(document.createTextNode(resource.value.toString())) // TODO need to convert this to a hex string
        addElement(element = element)
    }

    override fun addDimension(resource: DimensionResource) {
        // <dimen name="dimension_name">12dp</dimen>
        val value = when (val v = resource.value) {
            is Pixels -> "${v}px"
            is DependencyIndependentPixels -> "${v}dp"
            is ScaledPixels -> "${v}sp"
            is PointPixels -> "${v}pt"
            else -> null
        }

        value?.let {
            val element = document.createElement(TAG_NAME_DIMEN)
            element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)

            element.appendChild(document.createTextNode(value))
            addElement(element = element)
        }
    }

    override fun addFloat(resource: FloatResource) {
        // <item name="float_name" type="dimen" format="float">1.0</item>
        val element = document.createElement(TAG_NAME_FLOAT)
        element.setAttribute(ATTRIBUTE_NAME, resource.identifier.id)
        element.setAttribute(ATTRIBUTE_TYPE, "dimen")
        element.setAttribute(ATTRIBUTE_FORMAT, "float")
        element.appendChild(document.createTextNode(resource.value.toString()))
        addElement(element = element)
    }

    private fun addElement(element: Element) {
        resourcesElement.appendChild(element)
    }
}
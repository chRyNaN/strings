package com.chrynan.resources

import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult

class AndroidResourcesCreator(private val androidResourceLocation: String) {

    companion object {

        private const val XML_FILE_SUFFIX = "xml"
    }

    private val transformerFactory: TransformerFactory by lazy { TransformerFactory.newInstance() }

    private val documentBuilder: DocumentBuilder by lazy {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder()
        } catch (exception: ParserConfigurationException) {
            throw IllegalStateException("Unable to create an XML DocumentBuilder object to create a Resources File.")
        }
    }

    fun createStringResourceFile(resourceFile: ResourceFile.StringResourcesFile) {
        val document = AndroidResourcesDocument.newInstance(documentBuilder = documentBuilder)

        resourceFile.apply {
            singleStringResources.forEach(document::addString)
            pluralStringResources.forEach(document::addPluralString)
            stringArrayResources.forEach(document::addStringArray)
        }

        document.writeToFile(resourceFile = resourceFile)
    }

    fun createBooleanResourceFile(resourceFile: ResourceFile.BooleanResourcesFile) {
        val document = AndroidResourcesDocument.newInstance(documentBuilder = documentBuilder)

        resourceFile.booleanResources.forEach(document::addBoolean)

        document.writeToFile(resourceFile = resourceFile)
    }

    fun createIntegerResourceFile(resourceFile: ResourceFile.IntegerResourcesFile) {
        val document = AndroidResourcesDocument.newInstance(documentBuilder = documentBuilder)

        resourceFile.integerResources.forEach(document::addInteger)
        resourceFile.integerArrayResources.forEach(document::addIntegerArray)

        document.writeToFile(resourceFile = resourceFile)
    }

    private val ResourceFile.outputFile: File
        get() = File("$androidResourceLocation${File.separator}$id.$XML_FILE_SUFFIX")

    private fun AndroidResourcesDocument.writeToFile(resourceFile: ResourceFile) {
        try {
            resourceFile.outputFile.createNewFile()

            val result = StreamResult(resourceFile.outputFile)

            val transformer = transformerFactory.newTransformer().apply {
                setOutputProperty(OutputKeys.ENCODING, "utf-8")
                setOutputProperty(OutputKeys.INDENT, "yes")
                setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
            }

            transformer.transform(source, result)
        } catch (e: Exception) {
            throw ResourceFileCreatorException(resourceFile = resourceFile, additionalMessage = e.message)
        }
    }
}
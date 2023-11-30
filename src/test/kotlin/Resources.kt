import java.io.File
import java.lang.System.lineSeparator
import java.net.URI

internal object Resources {

    fun readText(fileName: String, delimiter: String = lineSeparator()): String = readLines(fileName).joinToString(delimiter)

    fun readLines(fileName: String): List<String> = File(fileName.toResourceUri()).readLines()

    private fun String.findResource() = Unit::class.java.classLoader.getResource(this)

    private fun String.toResourceUri(): URI = findResource()?.toURI() ?: throw IllegalArgumentException("Cannot find: $this")

}
